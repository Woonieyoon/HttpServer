package httpserver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class CacheCheck implements Runnable {

	private CacheManager cacheManager;

	public CacheCheck(CacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(2000);
				System.out.println("===================");
				System.out.println("캐쉬 데이터 갯수" + cacheManager.getCacheList().size());
				for (CacheFileData cacheFileData : cacheManager.getCacheMap().values()) {

					if (!cacheFileData.getFile().exists()) {
						cacheManager.getCacheMap().remove(cacheFileData.getFile().getAbsolutePath());
						cacheManager.getCacheList().remove(cacheFileData);
					}

					File tempfile = new File(cacheFileData.getFile().getAbsolutePath());

					if (tempfile.lastModified() != cacheFileData.getFile().lastModified()) {
						byte[] array = Files.readAllBytes(tempfile.toPath());
						cacheFileData.setFilebyte(array);
					}
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
