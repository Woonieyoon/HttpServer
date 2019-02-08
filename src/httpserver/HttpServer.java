package httpserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HttpServer {

	private final int port;
	private ServerSocket listener;
	private ExecutorService executorService;
	private Boolean init;
	private CacheManager cacheManager;

	public HttpServer(int port) {
		this.port = port;
		init = false;
	}

	public void init() {

		if (init) {
			return;
		}
		try {
			cacheManager = new CacheManager();
			listener = new ServerSocket(port);
			executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void execute() {
		Socket socket;
		executorService.submit(new CacheCheck(cacheManager));
		try {
			while (true) {
				System.out.println("접속 대기중");
				socket = listener.accept();
				System.out.printf("Connected IP : %s, Port : %d\n", socket.getInetAddress(),
						socket.getPort());
				HandleSocket handleSocket = new HandleSocket(socket,cacheManager);
				handleSocket.init();
				executorService.submit(handleSocket);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		HttpServer httpServer = new HttpServer(8080);
		httpServer.init();
		httpServer.execute();
	}
}
