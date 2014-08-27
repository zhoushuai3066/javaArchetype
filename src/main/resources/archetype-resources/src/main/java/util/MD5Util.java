package ${package}.util;



import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.MessageDigest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 
 * @author zhoushuai
 *
 */
public class MD5Util {
	
	private static Logger logger = LoggerFactory.getLogger(MD5Util.class);
	// 十六进制表示字母
	private final static String[] hexDigits = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

	/**
	 * 转换字节数组为16进制字串
	 * 
	 * @param b
	 *            字节数组
	 * @return 16进制字串
	 */
	public static String byteArrayToHexString(byte[] b) {
		StringBuffer resultSb = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			resultSb.append(byteToHexString(b[i]));
		}
		return resultSb.toString();
	}

	/**
	 * 把字节转换为16进制字符
	 * 
	 * @param b
	 * @return
	 */
	public static String byteToHexString(byte b) {
		int n = b;
		if (n < 0)
			n = 256 + n;
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}

	/**
	 * 
	 * 字符串转换为MD5编码格式的字符串
	 * 
	 * @param origin
	 * @return
	 */
	public static String md5(String origin) {
		String r = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			r = byteArrayToHexString(md.digest(origin.getBytes()));
		} catch (Exception e) {
			if (logger.isErrorEnabled()) {
				if (logger.isErrorEnabled()) {
					logger.error(e.getMessage());
				}
			}
		}
		return r;
	}

	/**
	 * length>0获取字符串的MD5码的前length位十六进制数字符串;length<0;获取字符串的MD5码的后length位十六进制数字符串
	 * 
	 * @param origin
	 * @param length
	 *            要求字符串的MD5码返回的十六进制字符串表示位数
	 *            length>=1&&length<=32或者length>=-32&&length<=-1 :返回指定的长度；
	 * @return
	 */
	public static String md5(String origin, int length) {
		String res = null;
		if (length >= 1 && length <= 32) {
			res = md5(origin).substring(0, length);
		} else if (length <= -1 && length >= -32) {
			res = md5(origin).substring(32 + length);
		} else {
		}
		return res;
	}
	
	/**
	 * 将文件进行md5编码
	 * @param fileName
	 * @return
	 * @throws Exception
	 */
	public static String getMd5ByFile(String fileName)
			throws Exception {
		InputStream fis;
		fis = new FileInputStream(fileName);
		byte[] buffer = new byte[1024];
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		int numRead = 0;
		while ((numRead = fis.read(buffer)) > 0) {
			md5.update(buffer, 0, numRead);
		}
		fis.close();
		return byteArrayToHexString(md5.digest());
	}
	public static void main(String[] args) throws Exception {
		System.out.println(MD5Util.getMd5ByFile("d:\\1"));
		File file = new File("d:\\1");
		System.out.println(file.getAbsolutePath());
		
	}
}
