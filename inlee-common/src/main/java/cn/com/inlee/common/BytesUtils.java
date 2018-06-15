package cn.com.inlee.common;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

import cn.com.inlee.common.exception.ParamException;

public class BytesUtils {

	/**
	 * 取字节高四位数
	 * 
	 * @param b
	 * @return
	 */
	public static int getHigh4Bit(byte b) {
		return (b & 0xf0) >> 4;
	}

	/**
	 * 取字节低四位数
	 * 
	 * @param b
	 * @return
	 */
	public static int getLow4Bit(byte b) {
		return b & 0x0f;
	}

	/**
	 * 字节转bit字符串
	 * 
	 * @param b
	 * @return
	 */
	public static String byteToBit(byte b) {
		return "" + (byte) ((b >> 7) & 0x1) + (byte) ((b >> 6) & 0x1) + (byte) ((b >> 5) & 0x1)
				+ (byte) ((b >> 4) & 0x1) + (byte) ((b >> 3) & 0x1) + (byte) ((b >> 2) & 0x1) + (byte) ((b >> 1) & 0x1)
				+ (byte) ((b >> 0) & 0x1);
	}

	/**
	 * @param res
	 *            源byte数组
	 * @return 正常返回res[0],错误返回0
	 */
	public static byte bytes2Byte(byte[] res) {
		if (res == null) {
			return 0;
		}
		if (res.length < 1) {
			return 0;
		}
		return res[0];
	}

	/**
	 * @param res
	 *            源byte数组
	 * @param offset
	 *            正常返回res[offset],错误返回0
	 */
	public static byte bytes2Byte(byte[] res, int offset) {
		if (res == null) {
			return 0;
		}
		if (res.length < offset + 1) {
			return 0;
		}
		return res[offset];
	}

	/**
	 * 
	 * @param res
	 * @param offset
	 * @return
	 * @throws Exception
	 */
	public static short bytes2Short(byte[] res) throws ParamException {

		return bytes2Short(res, 0);
	}

	/**
	 * 
	 * @param res
	 * @param offset
	 * @return
	 * @throws Exception
	 */
	public static short bytes2Short(byte[] res, int offset) throws ParamException {

		short des = 0;
		if (res != null) {
			if (res.length >= (offset + 2)) {

				// 最低位
				short des0 = (short) (res[offset + 1] & 0xff);
				// 次最低位
				short des1 = (short) (res[offset] & 0xff);

				des1 <<= 8;

				des = (short) (des0 | des1);
			}
			else {
				throw new ParamException("参数异常，参数的长度小于2 则抛出");
			}

		}
		else {
			throw new ParamException("参数异常，参数为null");
		}

		return des;
	}

	public static byte[] short2Bytes(int res) {
		byte[] des = new byte[2];
		// 最低位
		des[1] = (byte) (res & 0xff);
		// 次低位
		des[0] = (byte) ((res >> 8) & 0xff);
		return des;
	}

	/**
	 * 大端模式
	 * 
	 * @param res
	 *            byte数组源数据
	 * @return 转换所得int型数据
	 * @throws ParamException
	 *             参数异常，若des为null或des的长度小于 4 则抛出
	 * 
	 */
	public static int bytes2Int(byte[] res) throws ParamException {

		return bytes2Int(res, 0);
	}

	/**
	 * 大端模式
	 * 
	 * @param res
	 *            byte数组源数据
	 * @param offset
	 *            偏移量，即数组中转换的起始位置
	 * @return 转换所得int型数据
	 * @throws Exception
	 *             参数异常，若des为null或des的长度小于offset + 4 则抛出
	 */
	public static int bytes2Int(byte[] res, int offset) throws ParamException {
		int des = 0;
		if (res != null) {
			if (res.length >= (offset + 4)) {
				des = ((res[offset + 3] & 0xff) | ((res[offset + 2] << 8) & 0xff00) | ((res[offset + 1] << 24) >>> 8)
						| (res[offset] << 24));
			}
			else {
				throw new ParamException("参数异常，参数的长度小于 4 则抛出");
			}

		}
		else {
			throw new ParamException("参数异常，参数为null");
		}

		return des;
	}

	/**
	 * 大端模式
	 * 
	 * @param res
	 *            int型源数据
	 * @return 转换后的一个新的byte数组
	 */
	public static byte[] int2Bytes(int res) {
		byte[] des = new byte[4];
		// 最低位
		des[3] = (byte) (res & 0xff);
		// 次低位
		des[2] = (byte) ((res >> 8) & 0xff);
		// 次高位
		des[1] = (byte) ((res >> 16) & 0xff);
		// 最高位
		des[0] = (byte) ((res >> 24) & 0xff);
		return des;
	}

	/**
	 * 大端模式
	 * 
	 * @param res
	 *            int型源数据
	 * @param des
	 *            目标byte数组
	 * @return 若des为null或des的长度小于 4返回0，正常返回4
	 */
	public static boolean int2Bytes(int res, byte[] des) {
		if (des != null) {
			if (des.length >= 4) {
				// 最低位
				des[3] = (byte) (res & 0xff);
				// 次低位
				des[2] = (byte) ((res >> 8) & 0xff);
				// 次高位
				des[1] = (byte) ((res >> 16) & 0xff);
				// 最高位
				des[0] = (byte) (res >>> 24);
				return true;
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
	}

	/**
	 * 大端模式
	 * 
	 * @param res
	 *            int型源数据
	 * @param des
	 *            目标byte数组
	 * @param offset
	 *            偏移量，即转换所得byte所存储的起始位置
	 * @return 若des为null或des的长度小于 4 + offset返回0，正常返回4
	 */
	public static boolean int2Bytes(int res, byte[] des, int offset) {
		if (des != null) {
			if (des.length >= (offset + 4)) {

				// 最低位
				des[offset + 3] = (byte) (res & 0xff);
				// 次低位
				des[offset + 2] = (byte) ((res >> 8) & 0xff);
				// 次高位
				des[offset + 1] = (byte) ((res >> 16) & 0xff);
				// 最高位,无符号右移。
				des[offset] = (byte) (res >>> 24);
				return true;
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
	}

	/**
	 * 大端模式
	 * 
	 * @param res
	 *            byte数组源数据
	 * @return 转换所得long型数据
	 * @throws ParamException
	 *             参数异常，若des为null或des的长度小于8 则抛出
	 */
	public static long bytes2Long(byte[] res) throws ParamException {

		return bytes2Long(res, 0);
	}

	/**
	 * 大端模式
	 * 
	 * @param res
	 *            byte数组源数据
	 * @param offset
	 *            偏移量，即数组中转换的起始位置
	 * @return 转换所得long型数据
	 * @throws ParamException
	 *             参数异常，若des为nExceptionfset + 8 则抛出
	 */
	public static long bytes2Long(byte[] res, int offset) throws ParamException {
		long des = 0;
		if (res != null) {
			if (res.length >= offset + 8) {
				// 最低位
				long des0 = res[offset + 7] & 0xff;
				// 次最低位
				long des1 = res[offset + 6] & 0xff;
				// 次低位
				long des2 = res[offset + 5] & 0xff;
				// 中低位
				long des3 = res[offset + 4] & 0xff;
				// 中高位
				long des4 = res[offset + 3] & 0xff;
				// 次高位
				long des5 = res[offset + 2] & 0xff;
				// 次最高位
				long des6 = res[offset + 1] & 0xff;
				// 最高位
				long des7 = res[offset + 0] & 0xff;

				// des0不变
				des1 <<= 8;
				des2 <<= 16;
				des3 <<= 24;
				des4 <<= 32;
				des5 <<= 40;
				des6 <<= 48;
				des7 <<= 56;
				des = (des0 | des1 | des2 | des3 | des4 | des5 | des6 | des7);
			}
			else {
				throw new ParamException("参数异常，参数的长度小于8则抛出");
			}

		}
		else {
			throw new ParamException("参数异常，参数为null");
		}

		return des;
	}

	/**
	 * 大端模式
	 * 
	 * @param res
	 *            long型源数据
	 * @return 转换后的一个新的byte数组
	 */
	public static byte[] long2Bytes(long res) {
		byte[] des = new byte[8];
		// 最低位
		des[7] = (byte) (res & 0xff);
		// 次最低位
		des[6] = (byte) ((res >> 8) & 0xff);
		// 次低位
		des[5] = (byte) ((res >> 16) & 0xff);
		// 中低位
		des[4] = (byte) ((res >> 24) & 0xff);
		// 中高位
		des[3] = (byte) ((res >> 32) & 0xff);
		// 次高位
		des[2] = (byte) ((res >> 40) & 0xff);
		// 次最高位
		des[1] = (byte) ((res >> 48) & 0xff);
		// 最高位
		des[0] = (byte) ((res >> 56) & 0xff);

		return des;
	}

	/**
	 * 大端模式
	 * 
	 * @param res
	 *            long型源数据
	 * @param des
	 *            目标byte数组
	 * @return 若des为null或des的长度小于 8返回0，正常返回8
	 */
	public static boolean long2Bytes(long res, byte[] des) {
		if (des != null) {
			if (des.length >= 8) {

				// 最低位
				des[7] = (byte) (res & 0xff);
				// 次最低位
				des[6] = (byte) ((res >> 8) & 0xff);
				// 次低位
				des[5] = (byte) ((res >> 16) & 0xff);
				// 中低位
				des[4] = (byte) ((res >> 24) & 0xff);
				// 中高位
				des[3] = (byte) ((res >> 32) & 0xff);
				// 次高位
				des[2] = (byte) ((res >> 40) & 0xff);
				// 次最高位
				des[1] = (byte) ((res >> 48) & 0xff);
				// 最高位
				des[0] = (byte) ((res >> 56) & 0xff);
				return true;
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
	}

	/**
	 * 大端模式
	 * 
	 * @param res
	 *            long型源数据
	 * @param des
	 *            目标byte数组
	 * @param offset
	 *            偏移量，即转换所得byte所存储的起始位置
	 * @return 若des为null或des的长度小于offset + 8则返回0，正常返回8
	 */
	public static boolean long2Bytes(long res, byte[] des, int offset) {
		if (des != null) {
			if (des.length >= (offset + 8)) {

				// 最低位
				des[offset + 7] = (byte) (res & 0xff);
				// 次最低位
				des[offset + 6] = (byte) ((res >> 8) & 0xff);
				// 次低位
				des[offset + 5] = (byte) ((res >> 16) & 0xff);
				// 中低位
				des[offset + 4] = (byte) ((res >> 24) & 0xff);
				// 中高位
				des[offset + 3] = (byte) ((res >> 32) & 0xff);
				// 次高位
				des[offset + 2] = (byte) ((res >> 40) & 0xff);
				// 次最高位
				des[offset + 1] = (byte) ((res >> 48) & 0xff);
				// 最高位
				des[offset] = (byte) ((res >> 56) & 0xff);
				return true;
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
	}

	/**
	 * @param res
	 *            byte数组源数据
	 * @return 转换所得新的String型数据，转换错误返回null
	 */
	public static String bytes2String(byte[] res, String charset) {
		String des = "";
		if (res != null) {
			des = (new String(res, Charset.forName(charset))).trim();
		}
		return des;
	}

	/**
	 * @param res
	 *            byte数组源数据
	 * @param offset
	 *            偏移量，即数组中转换的起始位置
	 * @return 转换所得新的String型数据，转换错误返回null
	 */
	public static String bytes2String(byte[] res, int offset) {
		String des = "";
		byte[] b = null;
		if (res != null) {
			if (res.length >= (offset + 1)) {
				b = new byte[res.length - offset];
				for (int i = offset; i < res.length; i++) {
					b[i - offset] = res[i];
				}
				des = (new String(b)).trim();
			}
		}

		return des;
	}

	/**
	 * @param res
	 *            byte数组源数据
	 * @param start
	 *            res从index=start处开始转换
	 * @param end
	 *            res在index=end-1处转换结束
	 * @return 转换所得String型数据，转换错误返回null
	 */
	public static String bytes2String(byte[] res, int start, int end) {
		byte[] b = null;
		String des = "";
		if (res != null && (start < end)) {
			if (res.length >= end) {
				b = new byte[end - start];
				for (int i = start; i < end; i++) {
					b[i - start] = res[i];
				}
				des = (new String(b)).trim();
			}

		}

		return des;
	}

	/**
	 * 字节数组转指定的编码字符串
	 * 
	 * @param bytes
	 * @param start
	 * @param length
	 * @param charset
	 * @return
	 */
	public static String bytes2String(byte[] bytes, int start, int length, String charset) {

		if (bytes == null || bytes.length < start || bytes.length < (start + length) || length == 0)
			return "";

		byte[] bs = new byte[length];

		for (int index = 0; index < length; index++) {
			bs[index] = bytes[start + index];
		}

		return (new String(bs, Charset.forName(charset))).trim();
	}

	/**
	 * 将字节数组从start到末尾转成字符串，并插入到des字符串尾部
	 * 
	 * @param res
	 *            byte数组源数据
	 * @param offset
	 *            从数组的index=offset处转换
	 * @param des
	 *            转换后字符串加在des尾部
	 * @return 转换所得新的String型数据，转换错误返回null
	 */
	public static String bytes2String(byte[] res, int start, String des) {

		return bytes2String(res, start, res.length, des, des.length());
	}

	/**
	 * 
	 * 将字符串转成字符串，并插入到指定的位置
	 * 
	 * @param res
	 *            byte数组源数据
	 * @param des
	 *            转换后字符串加在des的index=offset处
	 * @param offset
	 *            偏移量
	 * @return 转换所得新的String型数据，转换错误返回null
	 */
	public static String bytes2String(byte[] res, String des, int offset) {
		return bytes2String(res, 0, res.length, des, offset);
	}

	/**
	 * 
	 * 将字节数组从start到end-1处,转成字符串，并插入到des字符串的offset位置
	 * 
	 * @param res
	 *            byte数组源数据
	 * @param start
	 *            res从index=start处开始转换
	 * @param end
	 *            res在index=end-1处转换结束
	 * @param des
	 *            des 转换后字符串加在des的index=offsets处
	 * @param offset
	 *            des偏移量
	 * @return 转换所得String型数据，转换错误返回null; 例如 res = "abcd".getBytes(); des =
	 *         "1234"; offset = 2; 则：结果为"12abcd34"
	 */
	public static String bytes2String(byte[] res, int start, int end, String des, int offset) {
		byte[] b = null;
		String str = "";
		String strs = null;
		String stre = null;
		if (des == null) {
			return null;
		}
		if (res == null) {
			return null;
		}

		if ((res.length >= end) && (start < end)) {
			b = new byte[end - start];
			for (int i = start; i < end; i++) {
				b[i - start] = res[i];
			}
			str = (new String(b)).trim();
		}
		if (offset > des.length()) {
			return null;
		}
		strs = des.substring(0, offset);
		stre = des.substring(offset);
		if (strs != null && str != null && stre != null) {
			des = (strs + str + stre);
			return des;
		}
		else {
			return null;
		}

	}

	/**
	 * @param res
	 *            String源数据
	 * @return 转换所得byte[]型数据， 错误返回null
	 * @throws UnsupportedEncodingException
	 */
	public static byte[] string2Bytes(String res, String charset) throws UnsupportedEncodingException {
		byte[] des = null;
		if (res != null) {
			des = res.getBytes(charset);
		}
		return des;
	}

	/**
	 * @param res
	 *            String源数据
	 * @param des
	 *            目标byte数组，从des[0]开始接收由res转换所得byte
	 * @return 错误返回0，正常返回转换的byte[]长度
	 */
	public static int string2Bytes(String res, byte[] des) {
		// 临时byte数组
		byte[] b = null;
		// 返回值
		int r = 0;
		if (res != null) {
			if ((b = res.getBytes()) != null) {
				r = b.length;
			}
			else {
				return 0;
			}
		}
		else {
			return 0;
		}
		if (des != null) {
			if (des.length >= r) {
				for (int i = 0; i < r; i++) {
					des[i] = b[i];
				}
				return r;
			}
			else {
				return 0;
			}
		}
		else {
			return 0;
		}
	}

	/**
	 * @param res
	 *            String源数据
	 * @param des
	 *            目标byte数组，从des[offset]开始接收由res转换所得byte
	 * @param offset
	 *            des偏移量
	 * @return 错误返回0，正常返回转换的byte[]长度
	 */
	public static int string2Bytes(String res, byte[] des, int offset) {
		// 临时byte数组
		byte[] b = null;
		// 返回值
		int r = 0;
		if (res != null) {
			if ((b = res.getBytes()) != null) {
				r = b.length;
			}
			else {
				return 0;
			}
		}
		else {
			return 0;
		}
		if (des != null) {
			if (des.length >= (r + offset)) {
				for (int i = 0; i < r; i++) {
					des[offset + i] = b[i];
				}
				return r;
			}
			else {
				return 0;
			}
		}
		else {
			return 0;
		}
	}

	/**
	 * @param res
	 *            String源数据
	 * @param des
	 *            目标byte数组，从des[offset]开始接收由res转换所得byte
	 * @param offset
	 *            des偏移量
	 * @param len
	 *            要转换的字节数
	 * @return 错误返回0，正常返回转换的byte[]长度
	 */
	public static int string2Bytes(String res, byte[] des, int offset, int len) {
		// 临时byte数组
		byte[] b = null;
		// 返回值
		int r = 0;
		if (res != null) {
			if ((b = res.getBytes()) != null) {
				r = b.length;
			}
			else {
				return 0;
			}
		}
		else {
			return 0;
		}
		if (des == null) {
			return 0;
		}
		r = ((len > r) ? r : len);
		if (des.length >= (len + offset)) {
			for (int i = 0; i < r; i++) {
				des[i + offset] = b[i];
			}
			if (len > r) {
				for (int j = 0; j < (len - r); j++) {
					des[j + offset + r] = 0;
				}
			}
			return len;
		}
		else {
			return 0;
		}
	}

	/**
	 * @param res
	 *            String源数据
	 * @param offset
	 *            开始转换的起始位置
	 * @return 错误返回null
	 */
	public static byte[] string2Bytes(String res, int offset) {
		byte[] des = null;
		if (res != null) {
			if (res.length() >= offset) {
				des = (res.substring(offset)).getBytes();
			}
		}
		return des;
	}

	/**
	 * @param res
	 *            String源数据
	 * @param offset
	 *            res偏移量，从res的index=offset处开始转换
	 * @param des
	 *            目标byte数组，从des[0]开始接收由res转换所得byte
	 * @return 错误返回0，正常返回转换的byte[]长度
	 */
	public static int string2Bytes(String res, int offset, byte[] des) {
		byte[] b = null;
		int r = 0;
		if (res == null) {
			return 0;
		}
		if (res.length() <= offset) {
			return 0;
		}
		if ((b = (res.substring(offset)).getBytes()) == null) {
			return 0;
		}
		r = b.length;
		if (des == null) {
			return 0;
		}
		if (des.length >= r) {
			for (int i = 0; i < r; i++) {
				des[i] = b[i];
			}
			return r;
		}
		else {
			return 0;
		}
	}

	/**
	 * @param res
	 *            String源数据
	 * @param offsets
	 *            res偏移量，从res的index=offsets处开始转换
	 * @param des
	 *            目标byte数组，从des[offsetb]开始接收由res转换所得byte
	 * @param offsetb
	 *            des偏移量
	 * @return 错误返回0，正常返回转换的byte[]长度
	 */
	public static int string2Bytes(String res, int offsets, byte[] des, int offsetb) {
		byte[] b = null;
		int r = 0;
		if (res == null) {
			return 0;
		}
		if (res.length() <= offsets) {
			return 0;
		}
		if ((b = (res.substring(offsets)).getBytes()) == null) {
			return 0;
		}
		r = b.length;
		if (des == null) {
			return 0;
		}
		if (des.length >= (r + offsetb)) {
			for (int i = 0; i < r; i++) {
				des[i + offsetb] = b[i];
			}
			return r;
		}
		else {
			return 0;
		}
	}

	/**
	 * @param res
	 *            String源数据
	 * @param offsets
	 *            res偏移量，从res的index=offsets处开始转换
	 * @param des
	 *            目标byte数组，从des[offsetb]开始接收由res转换所得byte
	 * @param offsetb
	 *            des偏移量
	 * @param len
	 *            要转换的字节数
	 * @return 错误返回0，正常返回转换的byte[]长度
	 */
	public static int string2Bytes(String res, int offsets, byte[] des, int offsetb, int len) {
		byte[] b = null;
		int r = 0;
		if (res == null) {
			return 0;
		}
		if (res.length() <= offsets) {
			return 0;
		}
		if ((b = (res.substring(offsets)).getBytes()) == null) {
			return 0;
		}
		r = b.length;
		if (des == null) {
			return 0;
		}
		r = ((len > r) ? r : len);
		if (des.length >= (len + offsetb)) {
			for (int i = 0; i < r; i++) {
				des[i + offsetb] = b[i];
			}
			if (len > r) {
				for (int j = 0; j < (len - r); j++) {
					des[j + offsetb + r] = 0;
				}
			}
			return len;
		}
		else {
			return 0;
		}
	}

	/**
	 * @param res
	 *            String源数据
	 * @param start
	 *            res从index=start处开始转换
	 * @param end
	 *            res在index=end-1处转换结束
	 * @return 错误返回null
	 */
	public static byte[] string2Bytes(String res, int start, int end) {
		byte[] des = null;
		if (res != null) {
			if (res.length() >= end && end > start) {
				des = (res.substring(start, end)).getBytes();
			}
		}
		return des;
	}

	/**
	 * @param res
	 *            String源数据
	 * @param start
	 *            res从index=start处开始转换
	 * @param end
	 *            res在index=end-1处转换结束
	 * @param des
	 *            目标byte数组，从des[0]开始接收由res转换所得byte
	 * @return 错误返回0，正常返回转换的byte[]长度
	 */
	public static int string2Bytes(String res, int start, int end, byte[] des) {
		byte[] b = null;
		int r = 0;
		if (res == null || (start >= end)) {
			return 0;
		}
		if (res.length() < end) {
			return 0;
		}
		if ((b = (res.substring(start, end)).getBytes()) == null) {
			return 0;
		}
		r = b.length;
		if (des == null) {
			return 0;
		}
		if (des.length >= r) {
			for (int i = 0; i < r; i++) {
				des[i] = b[i];
			}
			return r;
		}
		else {
			return 0;
		}
	}

	/**
	 * @param res
	 *            String源数据
	 * @param start
	 *            res从index=start处开始转换
	 * @param end
	 *            res在index=end-1处转换结束
	 * @param des
	 *            目标byte数组，从des[offset]开始接收由res转换所得byte
	 * @param offset
	 *            des偏移量
	 * @return 错误返回0，正常返回转换的byte[]长度
	 */
	public static int string2Bytes(String res, int start, int end, byte[] des, int offset) {
		byte[] b = null;
		int r = 0;
		if (res == null || (start >= end)) {
			return 0;
		}
		if (res.length() < end) {
			return 0;
		}
		if ((b = (res.substring(start, end)).getBytes()) == null) {
			return 0;
		}
		r = b.length;
		if (des == null) {
			return 0;
		}
		if (des.length >= (r + offset)) {
			for (int i = 0; i < r; i++) {
				des[i + offset] = b[i];
			}
			return r;
		}
		else {
			return 0;
		}
	}

	/**
	 * @param res
	 *            String源数据
	 * @param start
	 *            res从index=start处开始转换
	 * @param end
	 *            res在index=end-1处转换结束
	 * @param des
	 *            目标byte数组，从des[offset]开始接收由res转换所得byte
	 * @param offset
	 *            des偏移量
	 * @param len
	 *            要转换的字节数
	 * @return 错误返回0，正常返回转换的byte[]长度
	 */
	public static int string2Bytes(String res, int start, int end, byte[] des, int offset, int len) {
		byte[] b = null;
		int r = 0;
		if (res == null || (start >= end)) {
			return 0;
		}
		if (res.length() < end) {
			return 0;
		}
		if ((b = (res.substring(start, end)).getBytes()) == null) {
			return 0;
		}
		r = b.length;
		if (des == null) {
			return 0;
		}
		r = ((len > r) ? r : len);
		if (des.length >= (len + offset)) {
			for (int i = 0; i < r; i++) {
				des[i + offset] = b[i];
			}
			if (len > r) {
				for (int j = 0; j < (len - r); j++) {
					des[j + offset + r] = 0;
				}
			}
			return len;
		}
		else {
			return 0;
		}
	}

	/**
	 * @param des
	 *            目标数组
	 * @param res
	 *            源数组
	 * @return 将源数组添加在目标数组尾部
	 * @since 本方法适合用于较小的数组的添加
	 */
	public static byte[] mergeBytes(byte[] des, byte[] res) {
		byte[] ret = null;
		int resLen = 0;
		int desLen = 0;
		int copyIndex = 0;
		if (res == null) {
			return des;
		}
		if ((resLen = res.length) == 0) {
			return des;
		}
		if (des == null) {
			ret = new byte[resLen];
		}
		else {
			desLen = des.length;
			ret = new byte[desLen + resLen];
			for (int i = 0; i < desLen; i++) {
				ret[i] = des[i];
			}
			copyIndex = desLen;
		}
		for (int j = 0; j < resLen; j++) {
			ret[copyIndex + j] = res[j];
		}
		return ret;
	}

	/**
	 * @param des
	 *            目标数组
	 * @param res
	 *            源数组
	 * @param offset
	 *            des的偏移量，如：des={1,2,3,4,5,8}; res={6,7};
	 *            则返回数组ret=bytesAdd(des,5,res);使得ret={1,2,3,4,5,6,7,8}
	 * @since 本方法适合用于较小的数组的添加
	 */
	public static byte[] mergeBytes(byte[] des, int offset, byte[] res) {
		byte[] ret = null;
		int resLen = 0;
		int desLen = 0;
		int copyIndex = 0;
		if (res == null) {
			return des;
		}
		if ((resLen = res.length) == 0) {
			return des;
		}
		if (des == null) {
			ret = new byte[resLen];
			copyIndex = 0;
		}
		else {
			desLen = des.length;
			ret = new byte[desLen + resLen];
			for (int i = 0; i < offset; i++) {
				ret[i] = des[i];
			}
			copyIndex = offset;
		}
		for (int j = 0; j < resLen; j++) {
			ret[copyIndex + j] = res[j];
		}
		copyIndex += resLen;
		for (int k = 0; k < (desLen - offset); k++) {
			ret[copyIndex + k] = des[offset + k];
		}

		return ret;
	}

	/**
	 * 截取字节流
	 * 
	 * @param res
	 *            被截取的源字节数组
	 * @param offset
	 *            截取的第一个字节为res[offset],至res最后一个字节
	 * @return 截取到的新的字节数组
	 */
	public static byte[] subBytes(byte[] res, int offset) {

		if (res == null) {
			return null;
		}
		if (offset >= res.length) {
			return null;
		}
		byte[] subbytes = new byte[res.length - offset];
		for (int i = 0; i < res.length - offset; i++) {
			subbytes[i] = res[offset + i];
		}
		return subbytes;
	}

	/**
	 * 截取字节流
	 * 
	 * @param res
	 *            被截取的源字节数组
	 * @param start
	 *            截取的第一个字节为res[start]
	 * @param end
	 *            截取的最后一个字节为res[end - 1]
	 * @return 截取到的新的字节数组
	 */
	public static byte[] subBytes(byte[] res, int start, int end) {
		if (res == null || end <= start) {
			return null;
		}
		if (start >= res.length || end > res.length) {
			return null;
		}

		byte[] subbytes = new byte[end - start];
		for (int i = 0; i < end - start; i++) {
			subbytes[i] = res[start + i];
		}
		return subbytes;
	}

	/**
	 * 
	 * 
	 * @param des
	 *            目标byte数组
	 * @param start
	 *            des从index=start处开始置0
	 * @return 置0的元素个数， 错误返回0
	 */
	public static byte[] initializeArrayZero(byte[] des, int start) {
		int len = 0;
		byte[] ret = des;
		if (des == null) {
			return null;
		}
		if ((len = des.length) <= start) {
			return null;
		}
		len -= start;
		for (int i = 0; i < len; i++) {
			ret[start + i] = (byte) 0;
		}
		return ret;
	}

	/**
	 * @param des
	 *            目标byte数组
	 * @param start
	 *            des从index=start处开始置0
	 * @param len
	 *            置0的元素个数
	 * @return 置0的元素个数， 错误返回0
	 */
	public static byte[] initializeArrayZero(byte[] des, int start, int len) {
		byte[] ret = des;
		if (des == null) {
			return null;
		}
		if (des.length < (start + len)) {
			return null;
		}
		for (int i = 0; i < len; i++) {
			ret[start + i] = (byte) 0;
		}
		return ret;
	}

	/**
	 * 字节流转Hex字符串
	 * 
	 * @param src
	 * @return
	 */
	public static String bytes2HexString(byte[] bytes) {
		StringBuilder stringBuilder = new StringBuilder("");
		if (bytes == null || bytes.length <= 0) {
			return "";
		}
		for (int i = 0; i < bytes.length; i++) {
			int v = bytes[i] & 0xFF;
			String hv = Integer.toHexString(v);
			if (hv.length() < 2) {
				stringBuilder.append(0);
			}
			stringBuilder.append(hv);
		}
		return stringBuilder.toString();
	}

	/**
	 * 字节数组转Hex字符串
	 * 
	 * @param bytes
	 * @param start
	 * @param length
	 * @return
	 */
	public static String bytes2HexString(byte[] bytes, int start, int length) throws ParamException {

		if (bytes == null || bytes.length < start || bytes.length < (start + length))
			throw new ParamException("参数异常，参数为null或数组的长度小于取值范围");

		byte[] bs = new byte[length];

		for (int index = 0; index < length; index++) {
			bs[index] = bytes[start + index];
		}

		return bytes2HexString(bs);
	}

	public static String bytes2hexString(byte[] buffer, String separator) {

		StringBuffer sb = new StringBuffer(buffer.length * (separator.length() + 2));
		for (int i = 0; i < buffer.length; i++) {
			sb.append(Character.forDigit((buffer[i] & 240) >> 4, 16));
			sb.append(Character.forDigit(buffer[i] & 15, 16));
			sb.append(separator);
		}

		return sb.toString();
	}

	/**
	 * @param str
	 *            由UCS2编码的字符串
	 * @return UTF-8的编码字节数组，可用该数组生成UTF-8编码的字符串
	 */
	public static byte[] ucs2toUtf8(String str) {
		int p = 0;
		int i, len, c;
		len = str.length();
		byte[] ret = new byte[len * 3];
		for (i = 0; i < len; i++) {
			c = str.codePointAt(i);
			if (c >= 0x0001 && c <= 0x007f) { // 1-127之间
				ret[p] += c;
				p = p + 1;
			}
			else if (c > 0x07ff) {
				ret[p] += (0xE0 | ((c >> 12) & 0x0F));
				ret[p + 1] += (0x80 | ((c >> 6) & 0x3F));
				ret[p + 2] += (0x80 | ((c >> 0) & 0x3F));
				p = p + 3;
			}
			else {
				ret[p + 1] += (0xC0 | ((c >> 6) & 0x1F));
				ret[p + 2] += (0x80 | ((c >> 0) & 0x3F));
				p = p + 2;
			}
		}
		int truelen = 0;
		for (int j = 0; j < ret.length; j++) {
			if (ret[j] != 0) {
				truelen++;
			}
		}
		byte[] result = new byte[truelen];
		for (int j = 0; j < result.length; j++) {
			if (ret[j] != 0) {
				result[j] = ret[j];
			}
		}
		return result;
	}

	/**
	 * char[] 转 byte[]
	 * 
	 * @param chars
	 * @return
	 */
	public static byte[] char2bytes(char[] chars) {

		byte[] by = new byte[chars.length];
		for (int i = 0; i < chars.length; ++i) {
			by[i] = (byte) chars[i];
		}
		return by;
	}

	/**
	 * ACS码转BCD码
	 * 
	 * @param dest
	 * @param src
	 * @param length
	 * @return
	 */
	public static int AscToBcd(byte dest[], byte src[], int length) {
		int i;

		for (i = 0; i < length / 2; i++) {
			if (src[2 * i] >= '0' && src[2 * i] <= '9')
				dest[i] = (byte) ((src[2 * i] - '0') << 4);
			else if (src[2 * i] >= 'A' && src[2 * i] <= 'F')
				dest[i] = (byte) ((src[2 * i] - 'A' + 10) << 4);
			else if (src[2 * i] >= 'a' && src[2 * i] <= 'f')
				dest[i] = (byte) ((src[2 * i] - 'a' + 10) << 4);
			else
				return -1;

			if (src[2 * i + 1] >= '0' && src[2 * i + 1] <= '9')
				dest[i] += src[2 * i + 1] - '0';
			else if (src[2 * i + 1] >= 'A' && src[2 * i + 1] <= 'F')
				dest[i] += src[2 * i + 1] - 'A' + 10;
			else if (src[2 * i + 1] >= 'a' && src[2 * i + 1] <= 'f')
				dest[i] += src[2 * i + 1] - 'a' + 10;
			else
				return -1;
		}

		if (length % 2 != 0) {
			if (src[length - 1] >= '0' && src[length - 1] <= '9')
				dest[(length + 1) / 2 - 1] = (byte) ((src[length - 1] - '0') << 4);
			else if (src[length - 1] >= 'A' && src[length - 1] <= 'F')
				dest[(length + 1) / 2 - 1] = (byte) ((src[length - 1] - 'A' + 10) << 4);
			else if (src[length - 1] >= 'a' && src[length - 1] <= 'f')
				dest[(length + 1) / 2 - 1] = (byte) ((src[length - 1] - 'a' + 10) << 4);
			else
				return -1;
		}

		return 0;
	}

	/**
	 * BCD码转ACS码
	 * 
	 * @param dest
	 * @param src
	 * @param length
	 * @return
	 */
	public static int BcdToAsc(byte dest[], byte src[], int length) {
		byte ascii_table[] = new byte[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E',
				'F' };

		int i;

		for (i = 0; i < length / 2; ++i) {
			byte shr = (byte) ((src[i] & 0xFF) >> 4);
			dest[2 * i] = ascii_table[shr];
			dest[2 * i + 1] = ascii_table[src[i] & 0x0F];
		}

		if (length % 2 != 0) {
			byte shr = (byte) ((src[(length + 1) / 2 - 1] & 0xFF) >> 4);
			dest[length - 1] = ascii_table[shr];
		}
		return 0;
	}

}
