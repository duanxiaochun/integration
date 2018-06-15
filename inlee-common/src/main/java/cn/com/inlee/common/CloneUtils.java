package cn.com.inlee.common;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.Map;

public class CloneUtils {

	

	/**
	 * 克隆一个对象
	 * 
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public static Object cloneObject(Object obj) throws Exception {
		ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
		ObjectOutputStream out = new ObjectOutputStream(byteOut);
		out.writeObject(obj);
		ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
		ObjectInputStream in = new ObjectInputStream(byteIn);
		return in.readObject();
	}

	/**
	 * 同类对象属性复制(private,public) 复制出来的为新对象
	 * 
	 * @param object
	 * @return
	 * @throws Exception
	 */
	public static Object copyThisToNewOne(Object object) throws Exception {
		Class<?> classType = object.getClass();
		Object obj = classType.getConstructor(new Class[] {}).newInstance(new Object[] {});
		Field[] fields = classType.getDeclaredFields();
		for (Field field : fields) {
			if (Modifier.isPrivate(field.getModifiers())) {// 只需要私有字段
				String name = field.getName();
				String firstLetter = name.substring(0, 1).toUpperCase();
				String getMethodName = "get" + firstLetter + name.substring(1);
				String setMethodName = "set" + firstLetter + name.substring(1);
				Method getMethod = classType.getMethod(getMethodName, new Class[] {});
				Method setMethod = classType.getMethod(setMethodName, new Class[] { field.getType() });
				Object value = getMethod.invoke(object, new Object[] {});
				if (value != null) {
					setMethod.invoke(obj, new Object[] { value });
				}
			}
			else {
				System.out.println(field.get(object));
				obj.getClass().getField(field.getName()).set((Object) obj, field.get(object));
			}
		}
		return obj;
	}

	/**
	 * 同类对象属性复制(private,public) 把2个已有的对象属性进行复制 此方法src代表来源数据对象，
	 * result代表要把src中的属性复制到的对象
	 * 
	 * @param object
	 * @return
	 * @throws Exception
	 */
	public static Object copyThisToAnother(Object src, Object result) throws Exception {
		Class<?> classType = src.getClass();
		Object obj = classType.getConstructor(new Class[] {}).newInstance(new Object[] {});
		Field[] fields = classType.getDeclaredFields();
		for (Field field : fields) {
			if (Modifier.isPrivate(field.getModifiers())) {// 只需要私有字段
				String name = field.getName();
				String firstLetter = name.substring(0, 1).toUpperCase();
				String getMethodName = "get" + firstLetter + name.substring(1);
				String setMethodName = "set" + firstLetter + name.substring(1);
				Method getMethod = classType.getMethod(getMethodName, new Class[] {});
				Method setMethod = classType.getMethod(setMethodName, new Class[] { field.getType() });
				Object value = getMethod.invoke(src, new Object[] {});
				if (value != null) {
					setMethod.invoke(result, new Object[] { value });
				}
			}
			else {
				System.out.println(field.get(src));
				obj.getClass().getField(field.getName()).set(result, field.get(src));
			}
		}
		return result;
	}
	
	/**
	 * 
	 * 
	 * @param obj
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static boolean isLength(Object obj, int length) {

		if (obj == null) {
			return false;

		}
		else if (obj instanceof String) {
			return obj.toString().trim().length() == length;

		}
		else if (obj instanceof Collection) {
			return ((Collection) obj).size() == length;

		}
		else if (obj instanceof Map) {
			return ((Map) obj).size() == length;

		}
		else if (obj instanceof Object[]) {
			return ((Object[]) obj).length == length;

		}

		return false;

	}

}
