package httpserver;

import java.net.Socket;

public interface Executor {
	public void execute(Socket socket, CacheFileData cacheFileData);
}
