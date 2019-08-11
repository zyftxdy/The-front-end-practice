package com.zhang.util;

import java.security.MessageDigest;

/**
 * 用户密码MD5加密算法
 * @author 12443
 *
 */
public class MD5Util {

	public final static String getMD5String(String s) {

		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

		try {
			byte[] btInput = s.getBytes();

			//获取MessageDigest对象
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");

			//使用指定的字节更新摘要
			messageDigest.update(btInput);

			//获得密文
			byte[] md = messageDigest.digest();

			//把密文转换成十六进制的字符串形式
			int j = md.length;

			char result[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {

				byte byte0 = md[i];

				result[k++] = hexDigits[byte0 >>> 4 & 0xf];
				result[k++] = hexDigits[byte0 & 0xf];
			}

			return new String(result);

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
		
}

