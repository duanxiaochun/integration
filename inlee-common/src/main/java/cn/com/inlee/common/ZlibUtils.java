package cn.com.inlee.common;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import cn.com.inlee.common.exception.OperationActionException;

public class ZlibUtils {

	private static int SIZE = 1024;
 
	/**
	 * 压缩直接数组
	 * 
	 * @param data
	 * @return
	 * @throws OperationActionException
	 */
	public static byte[] compress(byte[] data) throws OperationActionException {
		byte[] output = new byte[0];

		Deflater compresser = new Deflater();
		compresser.reset();
		compresser.setInput(data);
		compresser.finish();
		ByteArrayOutputStream bos = new ByteArrayOutputStream(data.length);
		try {
			byte[] buf = new byte[SIZE];
			while (!compresser.finished()) {
				int i = compresser.deflate(buf);
				bos.write(buf, 0, i);
			}
			output = bos.toByteArray();
		}
		catch (Exception e) {
			output = data;
			throw new OperationActionException("解压数据失败", e);
		} finally {
			try {
				bos.close();
			}
			catch (IOException e) {
				throw new OperationActionException("解压数据失败", e);
			}
		}
		compresser.end();
		return output;
	}

	/**
	 * 解压缩 字节数组
	 * 
	 * @param data
	 * @return
	 */
	public static byte[] decompress(byte[] data) throws OperationActionException{
		byte[] output = new byte[0];

		Inflater decompresser = new Inflater();
		decompresser.reset();
		decompresser.setInput(data);

		ByteArrayOutputStream o = new ByteArrayOutputStream(data.length);
		try {
			byte[] buf = new byte[SIZE];
			while (!decompresser.finished()) {
				int i = decompresser.inflate(buf);
				o.write(buf, 0, i);
			}
			output = o.toByteArray();
		}
		catch (Exception e) {
			output = data;
			throw new OperationActionException("解压数据失败", e);
		} finally {
			try {
				o.close();
			}
			catch (IOException e) {
				throw new OperationActionException("解压数据失败", e);
			}
		}

		decompresser.end();
		return output;
	}
 
	public static void main(String[] args) {

		String zip = "{adfosjfl;jsdlf023qu40lsndpfo8u2qo3nfsldnfv}";

		try {
			String hex = BytesUtils.bytes2HexString(ZlibUtils.compress(zip.getBytes()));
			System.out.println(hex);

			System.out.println(new String(ZlibUtils.decompress(StringUtils.hexString2Bytes(hex))));

		}
		catch (OperationActionException e) {
			e.printStackTrace();
		}
	}
	// 654a7972546b784a79792f4f537375787a69704f79556b7a4d4449754c44557879436e4f53796c497937636f4e53724d4e38354c4b38354a795573727177554164576b5175413d3d
}
