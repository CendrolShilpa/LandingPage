package utilityFiles;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyFileClass {
	
	 private static Properties properties;

	    static {
	        properties = new Properties();
	        try (FileInputStream input = new FileInputStream("D:\\003_Automation\\Landing Pages\\006_Script\\LandingPages\\src\\main\\java\\files\\properties.properties")) {
	            properties.load(input);
	        } catch (IOException e) {
	            e.printStackTrace(); 
	        }
	    }

	    public static String getLocator(String key) {
	        return properties.getProperty(key);
	    }

}
