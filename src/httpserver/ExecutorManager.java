package httpserver;

import java.util.HashMap;
import java.util.Map;

public class ExecutorManager {
	
	private Map<String,Executor> executorMap;
	
	public ExecutorManager() {
		executorMap = new HashMap<String, Executor>();
	}
	
	public void init() {
		executorMap.put("html", new HtmlResponse());
		executorMap.put("jpg", new ImageResponse());
	}
	
	public Map<String,Executor> getExecutor(){
		return executorMap;
	}

}
