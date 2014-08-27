package ${package}.util;


import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ${package}.common.Constants;

/**
 * 
 * @author zhoushuai
 *
 */
public class StopInstance {
	private static Logger logger = LoggerFactory.getLogger(StopInstance.class);

	
	/**
	 * 判断.sleep标志文件，执行暂停动作
	 * 
	 * @param 
	 * @return 
	 */
	public static void doSleep() {
		boolean sleepFlag = new File(Constants.PROCESS_SLEEP_FILE).exists();
		if(sleepFlag){
			logger.info("DataPollThread is sleeping...");
		}
		while(sleepFlag){
			try {
				Thread.sleep(Constants.SLEEP_TIME);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			sleepFlag = new File(Constants.PROCESS_SLEEP_FILE).exists();
			if(!sleepFlag){
				logger.info("DataPollThread is wakeup...");
			}
		}
	}
}