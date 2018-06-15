package cn.com.inlee.common;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
 


public class ReflectUtils {

	@SuppressWarnings("unused")
	public static Field[] getAllField(Object obj) {
		for (Class<?> superClass = obj.getClass(); superClass != Object.class; superClass = superClass
				.getSuperclass()) {
			return superClass.getDeclaredFields();
		}
		return null;
	}

	/**
	 * 获取obj对象fieldName的Field
	 * 
	 * @param obj
	 * @param fieldName
	 * @return
	 */
	public static Field getFieldByFieldName(Object obj, String fieldName) {
		for (Class<?> superClass = obj.getClass(); superClass != Object.class; superClass = superClass
				.getSuperclass()) {
			try {
				return superClass.getDeclaredField(fieldName);
			} catch (NoSuchFieldException e) {
			}
		}
		return null;
	}

	/**
	 * 获取obj对象fieldName的属性值
	 * 
	 * @param obj
	 * @param fieldName
	 * @return
	 * @throws SecurityException
	 * @throws NoSuchFieldException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public static Object getValueByFieldName(Object obj, String fieldName)
			throws SecurityException, NoSuchFieldException,
			IllegalArgumentException, IllegalAccessException {
		Field field = getFieldByFieldName(obj, fieldName);
		Object value = null;
		if (field != null) {
			if (field.isAccessible()) {
				value = field.get(obj);
			} else {
				field.setAccessible(true);
				value = field.get(obj);
				field.setAccessible(false);
			}
		}
		return value;
	}

	/**
	 * 设置obj对象fieldName的属性值
	 * 
	 * @param obj
	 * @param fieldName
	 * @param value
	 * @throws SecurityException
	 * @throws NoSuchFieldException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public static void setValueByFieldName(Object obj, String fieldName,
			Object value) throws SecurityException, NoSuchFieldException,
			IllegalArgumentException, IllegalAccessException {
		Field field = obj.getClass().getDeclaredField(fieldName);
		if (field.isAccessible()) {
			field.set(obj, value);
		} else {
			field.setAccessible(true);
			field.set(obj, value);
			field.setAccessible(false);
		}
	}

	@SuppressWarnings("rawtypes")
	public static Object setValuesByMap(Map<String,String> from, Class type)
			throws SecurityException, NoSuchFieldException,
			IllegalArgumentException, IllegalAccessException,
			InstantiationException {

		Object to = type.newInstance();

		for (Iterator iter = from.keySet().iterator(); iter.hasNext();) {
			String fieldName = (String) iter.next();
			Object fieldValue = from.get(fieldName);

			Field field = to.getClass().getDeclaredField(fieldName);
			// 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
			// valueStr = new String(valueStr.getBytes("ISO-8859-1"), "UTF-8");
			if (StringUtils.isNotEmpty(field))
				setInstanceValue(field, to, fieldValue);
		}
		
		return to;

	}

	private static void setFiledValue(Field field, Object instance, Object value)
			throws IllegalArgumentException, IllegalAccessException {
		if (field.isAccessible()) {
			field.set(instance, value);
		} else {
			field.setAccessible(true);
			field.set(instance, value);
			field.setAccessible(false);
		}
	}

	private static void setInstanceValue(Field field, Object instance,
			Object value) throws IllegalArgumentException,
			IllegalAccessException {
		if (value == null) {
			return;
		}

		if (field.getType().equals(Date.class)) {
			setFiledValue(field, instance, DateUtils.parse(value.toString(),
					DATE_TIME_PATTERN));
			return;

		} else if (field.getType().equals(Double.class)) {
			setFiledValue(field, instance, Double.parseDouble(value.toString()));
			return;

		} else if (field.getType().equals(String.class)) {
			setFiledValue(field, instance, value.toString());
			return;

		} else if (field.getType().equals(Long.class)) {
			setFiledValue(field, instance, Long.parseLong(value.toString()));
			return;

		} else if (field.getType().equals(Integer.class)) {
			setFiledValue(field, instance, Integer.parseInt(value.toString()));
			return;
		} else if (field.getType().equals(Float.class)) {
			setFiledValue(field, instance, Float.parseFloat(value.toString()));
			return;
		}
	}

	@SuppressWarnings("rawtypes")
	public static boolean isWrapClass(Class clz) {
		try {
			return ((Class) clz.getField("TYPE").get(null)).isPrimitive();
		} catch (Exception e) {
			return false;
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Map map = new HashMap<String, String>();
		map.put("notify_time", "2016-01-12 12:50:23");
		map.put("notify_type", "APP");
		map.put("total_fee", "1.00");

		try {
//			ReflectHelper.setValuesByMap(map,
//					Class.forName(Test.class.getName()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
}