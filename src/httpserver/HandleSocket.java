package httpserver;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.nio.file.Files;

//path는 절대경로, 상대경로가 있다.
// www.naver.com/index.html index.html은 디스크에 어디에 저장되어 있다.
// webroot가 필요
// c://webroot/index.html

public class HandleSocket implements Runnable {

	private final String WEB_ROOT = "C:/HttpTest";
	private final Socket socket;
	private CacheManager cacheManager;
	private ExecutorManager executorManager;

	public HandleSocket(Socket socket, CacheManager cacheManager) {
		this.socket = socket;
		this.cacheManager = cacheManager;
	}

	public void init() {
		executorManager = new ExecutorManager();
		executorManager.init();
	}

	@Override
	public void run() {
		makeResponse();
	}

	public void makeResponse() {

		try (InputStream in = socket.getInputStream();
				BufferedReader br = new BufferedReader(new InputStreamReader(in));) {

			String header = br.readLine();
			String[] data = header.split(" ");
			String path = data[1];

			String fileName = path.substring(path.lastIndexOf("/"));
			String[] fileNameAndExtension = fileName.split("\\.");
			boolean hasExtenstion = fileNameAndExtension.length >= 2;

			String extension = null;
			if (hasExtenstion) {
				extension = fileNameAndExtension[fileNameAndExtension.length - 1];
			}

			
			System.out.println("요청Path: [" + path + "]");
			
			if(path.equals("/favicon.ico"))
					return;
			
			System.out.println("확장자: [" + extension + "]");

			//캐시 가져오기 or생성
			CacheFileData cacheData = cacheManager.getCacheMap().containsKey(WEB_ROOT + path)
					? cacheManager.getCacheMap().get(WEB_ROOT + path)
					: makeCache(WEB_ROOT + path);

					
			System.out.println(cacheData.getFile().getAbsolutePath());
			System.out.println(extension+"확장자");
			
			Executor executor = executorManager.getExecutor().get(extension);
			executor.execute(socket, cacheData);

		} catch (Exception e) {

		}
	}

	public CacheFileData makeCache(String filePath) {
		try {
			System.out.println("만들Path:" + filePath);
			System.out.println("만들었어");
			File file = new File(filePath);
			byte[] array = Files.readAllBytes(file.toPath());

			CacheFileData cacheFileData = new CacheFileData(file, array);
			synchronized (cacheManager) {
				cacheManager.getCacheMap().put(filePath, cacheFileData);
				cacheManager.getCacheList().add(cacheFileData);
			}
			return cacheFileData;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
