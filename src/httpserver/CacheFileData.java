package httpserver;

import java.io.File;

public class CacheFileData {

	private final File file;
	private byte[] filebyte;
	private int modifiedDate;
	private long lastModify;

	public CacheFileData(File file, byte[] filebyte, long lastModify) {
		this.file = file;
		this.filebyte = filebyte;
		this.lastModify = lastModify;
	}

	public long getLastModify() {
		return lastModify;
	}

	public void setLastModify(long lastModify) {
		this.lastModify = lastModify;
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
