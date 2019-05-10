package g.oa.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import g.oa.App;

public class PropertyUtil {
	private static final File CACHE_PROPERTIES_FILE = new File(App.class.getClassLoader().getResource("cache.properties").getFile());
	public static Properties loadCacheProperties() {
		Properties prop = new Properties();
		FileInputStream in = null;
		try {
			in = new FileInputStream(CACHE_PROPERTIES_FILE);
			prop.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if (in != null)
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		return prop;
	}
	
	public static void storeCacheProperties(String key,String value) {
		storeCacheProperties(key, value, true);
	}
	
	public static void storeCacheProperties(String key,String value,boolean reset) {
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(CACHE_PROPERTIES_FILE,true);
			Properties p = loadCacheProperties();
			if(p.getProperty(key)==null) {
				p.setProperty(key, value);
			}else {
				p.replace(key, value);
			}
			p.store(out,"");
			if(reset)
				App.resetCache();
		} catch ( IOException e) {
			e.printStackTrace();
		}finally {
			if(out != null)
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}
}
