package com.zhang.util;

import java.security.MessageDigest;

/**
 * �û�����MD5�����㷨
 * @author 12443
 *
 */
public class MD5Util {

	public final static String getMD5String(String s) {

		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

		try {
			byte[] btInput = s.getBytes();

			//��ȡMessageDigest����
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");

			//ʹ��ָ�����ֽڸ���ժҪ
			messageDigest.update(btInput);

			//�������
			byte[] md = messageDigest.digest();

			//������ת����ʮ�����Ƶ��ַ�����ʽ
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

