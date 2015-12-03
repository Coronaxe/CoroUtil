package at.coro.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {
	private String path;

	public ConfigManager(String configPath) {
		this.path = configPath;
	}

	public Properties loadConfig() throws IOException {
		Properties config = new Properties();
		InputStream iStream = new FileInputStream(this.path);
		config.load(iStream);
		return config;
	}

	public void saveConfig(Properties config) throws IOException {
		java.io.OutputStream oStream = new FileOutputStream(this.path);
		config.store(oStream, null);
	}

	public boolean configExists() {
		File propFile = new File(this.path);
		return propFile.exists();
	}

	public void deleteConfig() {
		File propFile = new File(this.path);
		propFile.delete();
	}
}
