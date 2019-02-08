package httpserver;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class HtmlResponse implements Executor {

	
	public HtmlResponse() {
		System.out.println("response 만들어짐");
	}

	@Override
	public void execute(Socket socket,  CacheFileData cacheFileData) {
		try (OutputStream out = socket.getOutputStream();
				PrintWriter pw = new PrintWriter(new OutputStreamWriter(out));) {

			File file = cacheFileData.getFile();
			long fileLength = file.length();
			pw.println("HTTP/1.1 200 OK");
			pw.println("Content-Type: text/html; charset=UTF-8");
			pw.println("Content-Length: " + fileLength);
			pw.println("Connection: close");
			pw.println("");
			pw.flush();

			byte[] array = cacheFileData.getFilebyte();
			out.write(array);
			out.flush();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
