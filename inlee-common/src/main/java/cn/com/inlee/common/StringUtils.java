package cn.com.inlee.common;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {

	private StringUtils() {
	}

	/**
	 * 检查对象是否为空
	 * 
	 * @param obj
	 *            java任意类型
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static boolean isEmpty(Object obj) {
		if (obj == null) {
			return true;

		}
		else if (obj instanceof String && (obj.toString().trim().equals(""))) {
			return true;

		}
		else if (obj instanceof Number && ((Number) obj).doubleValue() < 0) {
			return true;

		}
		else if (obj instanceof Collection && ((Collection) obj).isEmpty()) {
			return true;

		}
		else if (obj instanceof Map && ((Map) obj).isEmpty()) {
			return true;

		}
		else if (obj instanceof Object[] && ((Object[]) obj).length == 0) {
			return true;

		}
		return false;
	}

	/**
	 * 检查n个对象是否为空
	 * 
	 * @param obj
	 * @return
	 */
	public static boolean isEmpty(Object... obj) {
		boolean res = false;
		for (Object o : obj) {
			if (isEmpty(o)) {
				res = true;
				break;
			}
		}
		return res;
	}

	/**
	 * 检查对象是否不为空
	 * 
	 * @param obj
	 * @return
	 */
	public static boolean isNotEmpty(Object obj) {
		return !isEmpty(obj);
	}

	/**
	 * 检查对象是否不为空
	 * 
	 * @param obj
	 * @return
	 */
	public static boolean isNotEmpty(Object... obj) {
		boolean res = true;
		for (Object o : obj) {
			if (isEmpty(o)) {
				res = false;
			}
		}
		return res;
	}

	/**
	 * 检查对象是否为数字型字符串,包含负数开头的。
	 * 
	 * @param target
	 * @return
	 */
	public static boolean isNumeric(String target) {
		if (target == null) {
			return false;
		}
		char[] chars = target.toString().toCharArray();
		int length = chars.length;
		if (length < 1)
			return false;

		int i = 0;
		if (length > 1 && chars[0] == '-')
			i = 1;

		for (; i < length; i++) {
			if (!Character.isDigit(chars[i])) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 判断是否为中文
	 * 
	 * @param c
	 * @return
	 */
	public static boolean isChinese(char c) {
		Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
		if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
				|| ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
				|| ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
				|| ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
			return true;
		}
		return false;
	}

	public static boolean isChinese(String str) {
		if (null != str && str.length() > 0) {
			char[] chares = str.toCharArray();
			for (char c : chares) {
				if (isChinese(c))
					return true;
			}
		}
		else
			return false;

		return false;
	}

	/**
	 * 判断是否为中文乱码
	 * 
	 * @param strName
	 * @return
	 */
	public static boolean hasMessyCode(String target) {
		Pattern p = Pattern.compile("\\s*|\t*|\r*|\n*");
		Matcher m = p.matcher(target);
		String after = m.replaceAll("");
		String temp = after.replaceAll("}", "");
		char[] ch = temp.trim().toCharArray();
		float chLength = ch.length;
		float count = 0;
		for (int i = 0; i < ch.length; i++) {
			char c = ch[i];
			if (!Character.isLetterOrDigit(c)) {

				if (!isChinese(c)) {
					count = count + 1;
				}
			}
		}
		float result = count / chLength;
		if (result > 0.4) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * 判断传入字符串是否为数字类型
	 * 
	 * @param target
	 * @param isRadix
	 *            是否小数
	 * @return
	 */
	public static boolean isNumeric(String target, boolean isRadix) {
		if (null == target || "".equals(target.trim())) {
			return false;
		}
		if (isRadix) {
			if (target.matches("[0-9]+.[0-9]+")) {
				return true;
			}
		}
		else {
			if (target.matches("[0-9]+")) {
				return true;
			}
		}

		return false;
	}

	/**
	 * 把通用字符编码的字符串转化为汉字编码。
	 */
	public static String unicodeToChinese(String unicode) {
		StringBuilder out = new StringBuilder();
		if (!isEmpty(unicode)) {
			for (int i = 0; i < unicode.length(); i++) {
				out.append(unicode.charAt(i));
			}
		}
		return out.toString();
	}

	public static String toUnderlineStyle(String name) {
		StringBuilder newName = new StringBuilder();
		for (int i = 0; i < name.length(); i++) {
			char c = name.charAt(i);
			if (Character.isUpperCase(c)) {
				if (i > 0) {
					newName.append("_");
				}
				newName.append(Character.toLowerCase(c));
			}
			else {
				newName.append(c);
			}
		}
		return newName.toString();
	}

	public static String numberFormater(int number, int length) {
		NumberFormat formatter = NumberFormat.getNumberInstance();
		formatter.setMinimumIntegerDigits(length);
		formatter.setGroupingUsed(false);
		return formatter.format(number);
	}

	static final Pattern reUnicode = Pattern.compile("\\\\u([0-9a-zA-Z]{4})");

	public static String decode1(String s) {
		Matcher m = reUnicode.matcher(s);
		StringBuffer sb = new StringBuffer(s.length());
		while (m.find()) {
			m.appendReplacement(sb, Character.toString((char) Integer.parseInt(m.group(1), 16)));
		}
		m.appendTail(sb);
		return sb.toString();
	}

	public static String paddingLeft(String src, int len, String value) {

		while (src.length() < len) {
			src = value + src;
		}
		return src;
	}

	public static String paddingRight(String src, int len, String value) {
		while (src.length() < len) {
			src += value;
		}
		return src;
	}

	/**
	 * 
	 * @param value
	 * @param regex1
	 * @param regex2
	 * @return
	 */
	public static Map<String, String> string2Map(String value, String regex1, String regex2) {

		Map<String, String> map = new HashMap<String, String>();
		if (isEmpty(value)) {
			return map;
		}

		String[] tmp = value.split(regex1);

		for (String v : tmp) {
			String[] tmp1 = v.split(regex2);
			if (tmp1.length == 2) {
				map.put(tmp1[0], tmp1[1]);
			}
			else if (tmp1.length > 2) {
				map.put(tmp1[0], v.substring(v.indexOf(regex2)));
			}
			else {
				System.out.println("数据格式错误:" + v);
			}
		}

		return map;
	}

	public static boolean isEmail(String value) {
		if (value == null || (value.length() == 0)) {
			return false;
		}

		return value.indexOf("@") > -1;
	}

	public static boolean isCell(String value) {

		if (value == null || (value.length() == 0)) {
			return false;
		}
		// 1开头、数字、11位
		return value.startsWith("1") && isNumeric(value) && (value.length() == 11);
	}

	public static boolean isXml(String value) {

		return value.startsWith("<?xml");
	}

	public static Map<String, String> String2Map(String target, final String split) {
		if (target == null || target == "")
			return null;

		Map<String, String> map = new HashMap<String, String>();
		String[] res = target.split(split);
		String[] kv;
		for (int i = 0; i < res.length; i++) {
			if (res[i] == null || res[i] == "")
				continue;
			kv = res[i].split("=");
			if (kv.length == 2) {
				if (!map.containsKey(kv[0]))
					map.put(kv[0], kv[1]);
			}
			else if (kv.length > 2) {
				if (!map.containsKey(kv[0]))
					map.put(kv[0], res[i].substring(res[i].indexOf("=")));
			}
			else {

			}
		}
		return map;
	}

//	/**
//	 * 将16位byte[] 转换为32位String
//	 * 
//	 * @param buffer
//	 * @return String
//	 */
//	public static String toHex(byte buffer[]) {
//		StringBuffer sb = new StringBuffer(buffer.length * 2);
//		for (int i = 0; i < buffer.length; i++) {
//			sb.append(Character.forDigit((buffer[i] & 240) >> 4, 16));
//			sb.append(Character.forDigit(buffer[i] & 15, 16));
//		}
//
//		return sb.toString();
//	}



	public static byte[] hexString2Bytes(String hex) {
		byte[] res = new byte[hex.length() / 2];
		char[] chs = hex.toCharArray();
		for (int i = 0, c = 0; i < chs.length; i += 2, c++) {
			res[c] = (byte) (Integer.parseInt(new String(chs, i, 2), 16));
		}

		return res;
	}

	private static Pattern ChinesePattern = Pattern.compile("[\u4e00-\u9fa5]");

	/**
	 * 判断字符串是否包含中文字符
	 * 
	 * @param target
	 * @return
	 */
	public static boolean hasChinese(String target) {
		return ChinesePattern.matcher(target).find();
	}

	public static String substring(String src, int start, int length) {

		return src.substring(start, start + length);
	}

	/**
	 * 数字随机码码
	 * 
	 * @param length
	 * @return
	 */
	public static String getDigitRandom(int n) { // length表示生成字符串的长度
		String base = "0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < n; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}

	public static String getStringRandom(int n) { // length表示生成字符串的长度
		String base = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < n; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}

	/**
	 * 获取一个时间格式的编号
	 * 
	 * @param random
	 * @return yyyyMMddHHmmss + random(bit)
	 */
	public static String getIdentityByTime(int random) {

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		Date date = new Date();
		return dateFormat.format(date) + getDigitRandom(random);
	}

	public static String getUUID() {
		return UUID.randomUUID().toString();

	}

	public static String getUUIDNoSeparator() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString().replace("-", "");
	}

}
