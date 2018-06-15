package cn.com.inlee.common.security;

import java.io.UnsupportedEncodingException;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import cn.com.inlee.common.BytesUtils;
import cn.com.inlee.common.StringUtils;

public class AESUtils {

	public static void main(String[] a) throws Exception {

		String content = "1234567890abcde";
		String key = "0123456789abcdef"; // "This is TESTKEY!"
		// 加密
		System.out.println("加密前：" + content);
		System.out.println("密码：" + key);
		String encryptResult = "";
		// encryptResult = encryptAES(content, key);

		System.out.println("加密后：" + encryptResult);
		// 解密
		String decryptResult = "";
		// decryptResult = decryptAES(encryptResult, key);
		System.out.println("解密后：" + new String(decryptResult));
	}

	// private static final String IV_STRING = "16-Bytes--String";
	private static final String IV_STRING = "00000000000000000000000000000000";

	/**
	 * 加密
	 * 
	 * @param content
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static String encryptAES(String text, String key, String textCharSet) throws SecurityException {

		try {
			return BytesUtils.bytes2HexString(encryptAES(text.getBytes(textCharSet), key));
		}
		catch (UnsupportedEncodingException e) {
			throw new SecurityException("AES编码失败", e);
		}
	}

	/**
	 * 加密
	 * 
	 * @param content
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static byte[] encryptAES(byte[] text, String key) throws SecurityException {

		try {
			byte[] enCodeFormat = key.getBytes();
			SecretKeySpec secretKeySpec = new SecretKeySpec(enCodeFormat, "AES");
			byte[] initParam = StringUtils.hexString2Bytes(IV_STRING);// getBytes();

			IvParameterSpec ivParameterSpec = new IvParameterSpec(initParam);
			// 指定加密的算法、工作模式和填充方式

			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
			return cipher.doFinal(text);

		}
		catch (Exception e) {
			throw new SecurityException("AES加密失败", e);
		}

	}

	/**
	 * 解密
	 * 
	 * @param hexString
	 * @param key
	 * @param charSet
	 * @return
	 * @throws AESException
	 */
	public static String decryptAES(String hexString, String key, String charSet) throws SecurityException {

		try {
			return new String(decryptAES(StringUtils.hexString2Bytes(hexString), key), charSet);
		}
		catch (UnsupportedEncodingException e) {

			throw new SecurityException("AES解密失败！", e);
		}

	}

	/**
	 * 解密并转成hex字符串
	 * 
	 * @param content
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static String decryptHex(byte[] content, String key) throws SecurityException {

		return BytesUtils.bytes2HexString(decryptAES(content, key));
	}

	/**
	 * 解密内容
	 * 
	 * @param content
	 * @param key
	 * @return
	 * @throws AESException
	 */
	public static byte[] decryptAES(byte[] content, String key) throws SecurityException {

		byte[] enCodeFormat = key.getBytes();

		SecretKeySpec secretKey = new SecretKeySpec(enCodeFormat, "AES");

		byte[] initParam = StringUtils.hexString2Bytes(IV_STRING);

		IvParameterSpec ivParameterSpec = new IvParameterSpec(initParam);

		Cipher cipher;
		try {
			cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParameterSpec);
			return cipher.doFinal(content);
		}
		catch (Exception e) {
			throw new SecurityException("AES解密失败！", e);
		}

	}

}
