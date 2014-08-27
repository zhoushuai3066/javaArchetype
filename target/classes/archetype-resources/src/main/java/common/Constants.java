package ${package}.common;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;



/**
 * 
 * @author zhoushuai
 *
 */
public class Constants {
	
	/** 
	 * 进程轮询的最小时间间隔
	 */
	public static final int SLEEP_TIME = 1000 * Config.getInt("sleep.time");
	
	/**
	 * 主程序暂停标志
	 */
	public static final String PROCESS_SLEEP_FILE = Config.getString("process.sleep.file");
	
	
	public static ApplicationContext ac = null;
	
	static {
		ac = new ClassPathXmlApplicationContext("applicationContext.xml");
	}
}
