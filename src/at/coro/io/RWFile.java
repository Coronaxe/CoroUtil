package at.coro.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class RWFile {

	private File file;

	public RWFile(String filePath) {
		this.file = new File(filePath);
	}

	public boolean exists() {
		return this.file.exists();
	}

	public byte[] readFile() throws IOException {
		return Files.readAllBytes(Paths.get(this.file.getAbsolutePath()));
	}

	public void writeFile(byte[] data) throws IOException {
		Files.write(Paths.get(this.file.getAbsolutePath()), data);
	}
}
