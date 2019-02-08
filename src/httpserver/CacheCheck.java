package httpserver;

import java.io.File;

public class CacheCheck implements Runnable {

	private CacheManager cacheManager;

	public CacheCheck(CacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(8000);
				System.out.println("==============================================");
				System.out.println("캐쉬 데이터 갯수" + cacheManager.getCacheSet().size());
				for (File cacheFile : cacheManager.getCacheSet()) {
					if (!cacheFile.exists()) {
						cacheManager.remove(cacheFile);
					}

					File tempfile = new File(cacheFile.getAbsolutePath());

					// Arrays.equals(byte,byte)로 비교하는 방법도 있지만 데이터를 다 비교하기 때문에 너무 느리다.
					if (tempfile.lastModified() != cacheManager.get(cacheFile).getLastModify()) {
						cacheManager.remove(cacheFile);
					}
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
