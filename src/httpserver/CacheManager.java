package httpserver;

import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class CacheManager {

	//String Key값을 절대경로로 잡음
	private Map<File, CacheFileData> cashMap;
	
	public CacheManager() {
		cashMap = new TreeMap<>();
	}	
	
	public synchronized Set<File> getCacheSet() {
		return Collections.unmodifiableSet(cashMap.keySet());
	}
	
	@SuppressWarnings("unchecked")
	public synchronized List<CacheFileData> getCacheValue() {
		return (List<CacheFileData>) cashMap.values();
	}

	public synchronized CacheFileData get(File key) {
		return cashMap.get(key);
	}

	public synchronized void put(File key, CacheFileData cacheFileData) {
		cashMap.put(key, cacheFileData);
	}

	public synchronized void remove(File key) {
		cashMap.remove(key);
	}
	
	public synchronized CacheFileData findCache(String filePath) {

		File file = new File(filePath);
		if (cashMap.containsKey(file)) {
			System.out.println("찾았따.");
			return cashMap.get(file);
		}
		
		return null;
	}

}
