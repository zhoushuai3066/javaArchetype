package ${package}.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author zhoushuai
 *
 */
public class Config {
	protected static Logger logger = LoggerFactory.getLogger(Config.class);
	private static SimpleConfig m_instance = null;

	public static boolean getBoolean(String key) {
		return getInstance().getBoolean(key);
	}

	/**
	 * 唯一实例
	 * 
	 * @return
	 */
	private static SimpleConfig getInstance() {
		if (m_instance == null) {
			try {
				String resource = null;
				if (System.getProperty("config") == null) {
					resource = Thread.currentThread().getContextClassLoader().getResource("propertie.properties").getFile();
				} else {
					resource = System.getProperty("config");
				}
				m_instance = new SimpleConfig(resource);
			} catch (Exception e) {
				if (logger.isErrorEnabled()) {
//					logger.error(Util.getExceptionTrace(e));
				}
			}
		}
		return m_instance;
	}

	public static void main(String[] args) {
		System.out.println(Constants.SLEEP_TIME);
		System.out.println(System.getProperty("config"));
	}

	public static int getInt(String key) {
		return getInstance().getInt(key);
	}

	public static Properties getProperties() {
		return getInstance().getProperties();
	}

	public static String getRealPath(String file) {
		return getInstance().getRealPath(file);
	}

	public static String getRealPathByKey(String key) {
		return getRealPath(getString(key));
	}

	public static String getString(String key) {
		return getInstance().getString(key);
	}
}

class SimpleConfig {
	private Properties cache = null;

	@SuppressWarnings("unchecked")
	public SimpleConfig(Class c, String resource) throws IOException {
		this(c.getResourceAsStream(resource));
	}

	/**
	 * 用输入流初始化property
	 * 
	 * @param in
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public SimpleConfig(InputStream in) throws IOException {
		getCache().clear();
		getCache().load(in);
		in.close();
		getCache().putAll(System.getProperties());
		for (Enumeration keys = getCache().keys(); keys.hasMoreElements();) {
			String key = (String) keys.nextElement();
			String val = getCache().getProperty(key);
			getCache().remove(key);
			key = key.trim();
			val = val.trim();
			getCache().put(key, val);
		}
	}

	/**
	 * 以文件名初始化property
	 * 
	 * @param resource
	 * @throws IOException
	 */
	public SimpleConfig(String resource) throws IOException {
		this(new FileInputStream(resource));
	}

	public boolean getBoolean(String key) {
		String val = getString(key);
		return val.equalsIgnoreCase("true") || val.equalsIgnoreCase("y");
	}

	public boolean getBoolean(String key, boolean defaultValue) {
		String val = getString(key, String.valueOf(defaultValue));
		return val.equalsIgnoreCase("true") || val.equalsIgnoreCase("y");
	}

	private Properties getCache() {
		if (cache == null) {
			cache = new Properties();
		}
		return cache;
	}

	public int getInt(String key) {
		return Integer.parseInt(getString(key));
	}

	public int getInt(String key, int defaultValue) {
		return Integer.parseInt(getString(key, String.valueOf(defaultValue)));
	}

	public long getLong(String key) {
		return Long.parseLong(getString(key));
	}

	public double getDouble(String key) {
		return Double.parseDouble(getString(key));
	}

	public long getLong(String key, long defaultValue) {
		return Long.parseLong(getString(key, String.valueOf(defaultValue)));
	}

	public Properties getProperties() {
		return getCache();
	}

	public String getRealPath(String file) {
		return getRealPath("app.dir", file);
	}

	public String getRealPath(String key, String file) {
		String retval = getString(key);
		if (!retval.endsWith(File.separator)) {
			retval = retval.concat(File.separator);
		}
		if (file.startsWith(File.separator)) {
			file = file.substring(1, file.length());
		}
		return retval.concat(file);
	}

	public String getString(String key) {
		if (getCache().containsKey(key)) {
			return (String) getCache().get(key);
		}
		throw new IllegalArgumentException("undefined config property: " + key);
	}

	public String getString(String key, String defaultValue) {
		return getCache().containsKey(key) ? (String) getCache().get(key) : defaultValue;
	}
}
