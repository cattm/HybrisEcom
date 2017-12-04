package gratetech.bdd.commons;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

public class TestProperties {
	public static String TEST_PROPERTY_BASE = "/Users/marcus/Documents/edge2016/pando/src/test/resources/";
	public static String USER_BASEURL;
	public static String LOGGERFILE;
	public static String BROWSER;
	public static boolean DO_ASSERTCHECKS = false;
	public static String DO_SCREENSHOTS = "false";
	private static boolean loaded = false;

	
	private static Properties prop = new Properties();
	public static Logger log = Logger.getLogger(TestProperties.class); 
	 
	 public static void loadPropertyFile( String filename) throws IOException {
              
         if (loaded == false) {
        	 FileInputStream fis = new FileInputStream( TEST_PROPERTY_BASE + filename);
             prop.load(fis);
        	 loadProperties();
 
        	 loaded = true;
         } 
	 }

     private static void loadProperties() {
             USER_BASEURL = prop.getProperty("base.url");
             LOGGERFILE = prop.getProperty("test.logger");
             BROWSER = prop.getProperty("test.browser");
             //DO_ASSERTCHECKS = Boolean.parseBoolean(prop.getProperty("test.doasserts"));
             //DO_SCREENSHOTS = prop.getProperty("test.allimages");
     }
}
