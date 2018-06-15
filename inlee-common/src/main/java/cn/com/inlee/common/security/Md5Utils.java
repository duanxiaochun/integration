package cn.com.inlee.common.security;

public class Md5Utils {
	public static String md5(String md5) {
		if (null != md5 && md5.length() > 0)
			return md5(md5.getBytes());
		return null;
	}

	public static String md5(byte[] source) {
		String s = null;
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			java.security.MessageDigest md = java.security.MessageDigest
					.getInstance("MD5");
			md.update(source);
			byte tmp[] = md.digest();
			char str[] = new char[16 * 2];
			int k = 0;
			for (int i = 0; i < 16; i++) {
				byte byte0 = tmp[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			s = new String(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(s);
		return s;

	}
	// public static void main(String[] args) {
	// String resrult=getMD5("123");
	// System.out.println(resrult);
	// }
}
