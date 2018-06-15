package cn.com.inlee.common.security;

import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import cn.com.inlee.common.BytesUtils;
import cn.com.inlee.common.StringUtils;

public class DESUtils {

	public static String DES = "AES"; // optional value AES/DES/DESede

	public static String CIPHER_ALGORITHM = "AES"; // optional value
													// AES/DES/DESede
	public static String CHAT_SET = "UTF-8"; // optional value

	/**
	 * 根据charset加密数据，并返回hex字符串
	 * 
	 * @param data
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static String encrypt(String data, String key, String charSet) throws SecurityException {

		try {
			return BytesUtils.bytes2HexString(encrypt(data.getBytes(charSet), key));
		}
		catch (UnsupportedEncodingException e) {
			throw new SecurityException("DES编码失败!", e);
		}
	}

	/**
	 * 加密数据
	 * 
	 * @param data
	 * @param key
	 * @return
	 * @throws SecurityException
	 */
	public static byte[] encrypt(byte[] data, String key) throws SecurityException {

		try {

			Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
			cipher.init(Cipher.ENCRYPT_MODE, getSecretKey(key), new SecureRandom());
			return cipher.doFinal(data);
		}
		catch (Exception e) {
			throw new SecurityException("DES加密失败!", e);
		}

	}

	/**
	 * 将hex字符串解码成指定的charset字符串
	 * 
	 * @param hexString
	 * @param key
	 * @param charSet
	 * @return
	 * @throws Exception
	 */
	public static String detrypt(String hexString, String key, String charSet) throws SecurityException {
		try {
			return new String(detrypt(StringUtils.hexString2Bytes(hexString), key), charSet);
		}
		catch (UnsupportedEncodingException e) {
			throw new SecurityException("DES编码失败!", e);
		}
	}

	/**
	 * 将字节流解密
	 * 
	 * @param hexString
	 * @param key
	 * @return
	 * @throws SecurityException
	 */
	public static byte[] detrypt(byte[] text, String key) throws SecurityException {

		try {
			SecureRandom sr = new SecureRandom();
			Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
			Key securekey = getSecretKey(key);
			cipher.init(Cipher.DECRYPT_MODE, securekey, sr);
			return cipher.doFinal(text);
		}
		catch (Exception e) {
			throw new SecurityException("DES解密失败!", e);
		}

	}

	private static Key getSecretKey(String key) throws SecurityException {

		if (key == null) {
			throw new SecurityException("DES获取Key失败,Key为空值");
		}

		try {
			SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
			random.setSeed(key.getBytes());

			KeyGenerator kgen = KeyGenerator.getInstance(CIPHER_ALGORITHM);
			kgen.init(128, random);
			SecretKey secretKey = kgen.generateKey();
			byte[] enCodeFormat = secretKey.getEncoded();
			return new SecretKeySpec(enCodeFormat, CIPHER_ALGORITHM);
		}
		catch (NoSuchAlgorithmException e) {
			throw new SecurityException("DES获取Key失败!", e);
		}

	}

	public static void main(String[] args) throws Exception {
		// String message = "18983001336";
		// String key = "zf_123_!@#ABC";
		// String entryptedMsg = "";//encrypt(message, key);
		// System.out.println("encrypted message is below :");
		// System.out.println(entryptedMsg);
		//
		// String decryptedMsg = "";//detrypt(entryptedMsg, key);
		// System.out.println("decrypted message is below :");
		// System.out.println(decryptedMsg);
	}
}
