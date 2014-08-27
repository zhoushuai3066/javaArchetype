package ${package}.manager;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ${package}.common.Constants;
import ${package}.util.StopInstance;

/**
 * 
 * @author zhoushuai
 *
 */
public class DataThread implements Runnable {
	
	

	private static final Logger logger = LoggerFactory.getLogger(DataThread.class);


	/**
	 * @throws IOException 
	 */
	public void run() {
		logger.info("Process start!");
		int emptyTimes = 0;
		while (true) {
			System.out.println(111);
			StopInstance.doSleep();//
			if (process() > 0) {
				emptyTimes = 0;
			} else {
				emptyTimes++;
				emptyTimes = emptyTimes > 5 ? 5 : emptyTimes;
				try {
					Thread.sleep(emptyTimes * Constants.SLEEP_TIME);
				} catch (InterruptedException e) {
					logger.error("error"+e);
				}
			}
		}
	}

	/**
	 * 处理了数据与否
	 * 1表示处理了数据，0表示未处理数据
	 * @throws Exception 
	 */
	private int process() {
		int flag =0;
		
		if(true){
			flag =1;
		}
		
		return flag; 
	}


}
