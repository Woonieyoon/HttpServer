package httpserver;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class CacheManager {

	//String Key값을 절대경로로 잡음
	private Map<String, CacheFileData> cashMap;
	private List<CacheFileData> cacheList;
	
	public CacheManager() {
		cashMap = new TreeMap<>();
		cacheList = new ArrayList<CacheFileData>();
	}	
	
	public Map<String, CacheFileData> getCacheMap(){
		return cashMap; 
	}
	
	public List<CacheFileData> getCacheList(){
		return cacheList; 
	}
	
}
