package httpserver;

import java.io.BufferedReader;
import java.io.IOException;

public class StartLineParser {

	private BufferedReader br;

	public StartLineParser(BufferedReader br) {
		this.br = br;
	}

	public ParsedStartLine parse() {
		String header;
		try {
			header = br.readLine();
			String[] data = header.split(" ");
			String path = data[1];

			String fileName = path.substring(path.lastIndexOf("/"));
			String[] fileNameAndExtension = fileName.split("\\.");
			boolean hasExtenstion = fileNameAndExtension.length >= 2;

			String extension = null;
			if (hasExtenstion) {
				extension = fileNameAndExtension[fileNameAndExtension.length - 1];
			}

			return new ParsedStartLine(path, extension);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
