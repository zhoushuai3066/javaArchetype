package ${package}.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ${package}.common.Constants;

/**
 * 
 * @author zhoushuai
 *
 */
public class AppServer {

	private static final Logger logger = LoggerFactory.getLogger(AppServer.class);

	public static void main(final String[] args) {
		

		try {
			new Thread((DataThread) Constants.ac.getBean("dataThread")).start();
			logger.info("AppServer process starts!");			
		} catch (Exception e) {
			logger.error("process exception"+e);
			System.exit(1);
		}

		
	}
}
