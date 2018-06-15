package cn.com.inlee.common;

import java.nio.charset.Charset;

public class CRC16Utils {

	/**
	 * 计算CRC16校验码
	 *
	 * @param bytes
	 *            需效验的字节数组
	 * @return
	 */
	public static String calculateHexCRC(byte[] bytes) {
		return BytesUtils.bytes2HexString(calculateCRC(bytes));
	}

	/**
	 * 计算CRC16校验码
	 * 
	 * @param src 需效验的字符串
	 * @param charset 编码格式
	 * @return
	 */
	public static String calculateHexCRC(String src, String charset) {
		return BytesUtils.bytes2HexString(calculateCRC(src, charset));
	}

	/**
	 * 
	 * @param hex
	 *            16进制字符串
	 * @return
	 */
	public static byte[] calculateCRC(String hex) {
		return calculateCRC(StringUtils.hexString2Bytes(hex));
	}

	public static byte[] calculateCRC(String src, String charset) {
		return calculateCRC(src.getBytes(Charset.forName(charset)));
	}

	public static byte[] calculateCRC(byte[] bytes) {
		int CRC = 0x0000ffff;
		int POLYNOMIAL = 0x0000a001;
		int i, j;
		for (i = 0; i < bytes.length; i++) {
			CRC ^= ((int) bytes[i] & 0x000000ff);
			for (j = 0; j < 8; j++) {
				if ((CRC & 0x00000001) != 0) {
					CRC >>= 1;
					CRC ^= POLYNOMIAL;
				}
				else {
					CRC >>= 1;
				}
			}
		}
		return BytesUtils.short2Bytes(CRC);
	}

}
