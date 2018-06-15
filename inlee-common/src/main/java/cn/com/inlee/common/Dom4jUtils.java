package cn.com.inlee.common;

import java.io.StringReader;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.xml.sax.InputSource;

/**
 * @author 作者 jesse E-mail: 87392304@qq.com
 * @date 创建时间：2016-11-7 下午2:12:12
 * @version 1.0
 * @parameter
 * @since
 * @return
 */
public class Dom4jUtils {

	/**
	 * @param args
	 * @throws DocumentException
	 */
	public static String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

	/**
	 * 把xml格式转化为对象
	 * 
	 * @param xml
	 * @return
	 */
	public static <T> T fromXmlToBean(String xml, Class<T> clazz, String enconding) {

		InputSource in = new InputSource(new StringReader(xml));
		in.setEncoding(enconding); // "uft-8"
		SAXReader reader = new SAXReader();
		Document document;
		try {
			document = reader.read(in);
			Element root = document.getRootElement();
			return (T) Dom4jUtils.fromXmlToBean(root, clazz);

		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("数据解析错误");

		}
		return null;
	}

	// @SuppressWarnings("serial")
	// public static class InspurResponseAO implements Serializable {
	//
	// private String CODE;
	// private String MSG;
	//
	// /**
	// * @return the cODE
	// */
	// public String getCODE() {
	// return CODE;
	// }
	//
	// /**
	// * @param cODE
	// * the cODE to set
	// */
	// public void setCODE(String cODE) {
	// CODE = cODE;
	// }
	//
	// /**
	// * @return the mSG
	// */
	// public String getMSG() {
	// return MSG;
	// }
	//
	// /**
	// * @param mSG
	// * the mSG to set
	// */
	// public void setMSG(String mSG) {
	// MSG = mSG;
	// }
	//
	// }

	// @SuppressWarnings("serial")
	// public static class MMM extends InspurResponseAO implements Serializable
	// {
	// private String NEED_CODE;
	//
	// public String getNEED_CODE() {
	// return NEED_CODE;
	// }
	//
	// public void setNEED_CODE(String nEED_CODE) {
	// NEED_CODE = nEED_CODE;
	// }
	//
	// }
	//
	// public static void main(String[] a) {
	// String xml = "<xsm code=\"0001\" msg=\"用户名密码错误\"
	// trans_time=\"20170301163438\"><NEED_CODE>1</NEED_CODE></xsm>";
	// MMM mm = Dom4jUtils.fromXmlToBean(xml, MMM.class, "utf-8");
	// System.out.println(mm.getCODE());
	// }
	//

	public static <T> T fromXmlToBean(Element rootElt, Class<T> clazz) throws Exception {
		// 首先得到clazz所定义的字段
		clazz.getSuperclass();

		List<Field> listField = new ArrayList<Field>();
		for (Class<?> claz = clazz; claz != Object.class; claz = claz.getSuperclass()) {
			Field[] fields = claz.getDeclaredFields();
			for (Field f : fields)
				listField.add(f);
		}

		// 根据传入的Class动态生成clazz对象
		T obj = clazz.newInstance();
		for (Field field : listField) {
			// 设置字段可访问（必须，否则报错）
			field.setAccessible(true);
			// 得到字段的属性名
			String name = field.getName();

			// 这一段的作用是如果字段在Element中不存在会抛出异常，如果出异常，则跳过。
			// try {
			// String v = rootElt.elementTextTrim(name);
			// //System.out.println(name + " " + rootElt.elements(name).size() +
			// " -> " + v);
			// }
			// catch (Exception ex) {
			// continue;
			// }

			String value = getElementValue(rootElt, name);

			if (value != null && !"".equals(value)) {
				// 根据字段的类型将值转化为相应的类型，并设置到生成的对象中。
				if (field.getType().equals(Long.class) || field.getType().equals(long.class)) {
					field.set(obj, Long.parseLong(value));
				}
				else if (field.getType().equals(String.class)) {
					field.set(obj, value);
				}
				else if (field.getType().equals(Double.class) || field.getType().equals(double.class)) {
					field.set(obj, Double.parseDouble(value));
				}
				else if (field.getType().equals(Integer.class) || field.getType().equals(int.class)) {
					field.set(obj, Integer.parseInt(value));
				}
				else if (field.getType().equals(Boolean.class) || field.getType().equals(boolean.class)) {
					field.set(obj, Boolean.parseBoolean(value));
				}
				else if (field.getType().equals(Byte.class) || field.getType().equals(byte.class)) {
					field.set(obj, Byte.parseByte(value));
				}
				else if (field.getType().equals(Float.class) || field.getType().equals(float.class)) {
					field.set(obj, Float.parseFloat(value));
				}
				else if (field.getType().equals(Short.class) || field.getType().equals(short.class)) {
					field.set(obj, Short.parseShort(value));
				} // 以上类型都不是，则表明是对象
				else if (field.getType().equals(java.util.Date.class)) {
					field.set(obj, DateUtils.parse(value, DATE_TIME_PATTERN));
				}

				// else if (field.getType().equals(java.util.Date.class)) {
				// field.set(obj, DateUtils.parse(value,
				// Constants.DATE_TIME_PATTERN));
				// }
				else {
					continue;
				}
			}
		}
		return obj;
	}

	private static String getElementValue(Element element, String name) {

		String value = element.elementTextTrim(name);

		if (null == value || "" == value)
			value = element.attributeValue(name.toLowerCase());

		return value;

	}

	public static <T> String fromBeanToXml(final T t) {
		String clsName = t.getClass().getName();
		clsName = clsName.substring(clsName.lastIndexOf(".") + 1, clsName.length());
		System.out.println(clsName);

		// 根据类名生成root
		Document doc = DocumentHelper.createDocument();
		Element root = doc.addElement(clsName);
		root.addComment(t.getClass().getName() + " 的xml定义");

		// bean属性
		Element fieldsElems = root.addElement("fields");

		@SuppressWarnings("rawtypes")
		Class cls = t.getClass();
		Field[] fields = cls.getDeclaredFields();
		String fieldName = "";
		for (Field f : fields) {
			f.setAccessible(true);
			fieldName = f.getName();
			fieldsElems.addElement(fieldName);
		}

		// 方法
		Element methodElems = root.addElement("methods");
		Method[] methods = cls.getDeclaredMethods();
		String methodName = "";
		for (Method m : methods) {
			methodName = m.getName();
			Element methodElem = methodElems.addElement(methodName);
			// 生成这个方法的详细说明
			createMethodElem(methodElem, m);
		}

		return doc.asXML();
	}

	@SuppressWarnings("rawtypes")
	private static void createMethodElem(Element methodElem, Method m) {
		// 参数

		Class[] params = m.getParameterTypes();
		// 返回值
		Class<?> returnType = m.getReturnType();
		// 返回值
		Class<?>[] exceptions = m.getExceptionTypes();

		Element paramsElem = methodElem.addElement("params"); // <params>
		// 生成参数
		StringBuffer sb = new StringBuffer();
		String str = "";
		Element paramType = paramsElem.addElement("param-type");
		for (Class type : params) {
			sb.append(type.getName()).append(",");
		}
		str = sb.toString();
		paramType.addText(str);

		// 返回值
		Element returnElem = paramsElem.addElement("return-type");
		sb.delete(0, sb.length());
		sb.append(returnType.getName()).append(",");
		returnElem.addText(sb.toString());

		// 异常
		Element exceptionElem = paramsElem.addElement("exceptions");
		sb.delete(0, sb.length());
		for (Class exception : exceptions) {
			sb.append(exception.getName()).append(",");
		}
		exceptionElem.addText(sb.toString());
	}
}