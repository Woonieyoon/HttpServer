package httpserver;

import java.io.File;

public class CacheFileData {

	private final File file;
	private byte[] filebyte;
	private int modifiedDate;

	public CacheFileData(File file, byte[] filebyte) {
		this.file = file;
		this.filebyte = filebyte;
	}

	public int getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(int modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public void setFilebyte(byte[] filebyte) {
		this.filebyte = filebyte;
	}

	public File getFile() {
		return file;
	}

	public byte[] getFilebyte() {
		return filebyte;
	}


}
