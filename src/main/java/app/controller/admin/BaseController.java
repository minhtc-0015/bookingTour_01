package app.controller.admin;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

public class BaseController {
	private static final Logger logger = Logger.getLogger(BaseController.class);
	
	public Properties getProperties() {
		Properties prop = new Properties();
		try (InputStream input = new FileInputStream("src/main/resources/messages.properties")) {
			prop.load(input);
			return prop;
		} catch (IOException ex) {
			logger.error(ex);
			return null;
		}
	}
	
}
