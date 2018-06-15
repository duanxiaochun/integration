package cn.com.inlee.common;

import java.util.HashMap;
import java.util.Map;

public class MapConvertUtils {

	public static <K1, V1> MapBuilder<K1, V1> creator() {
		return new MapConvertUtils().new MapBuilder<K1, V1>();
	}

	public static MapBuilder<String, String> MAP_STRING = new MapConvertUtils().new MapBuilder<String, String>();
	public static MapBuilder<String, Object> MAP_OBJ = new MapConvertUtils().new MapBuilder<String, Object>();

	public class MapBuilder<K, V> {

		public MapBuilder() {
			map = new HashMap<K, V>();
		}

		public MapBuilder<K, V> put(K key, V value) {
			if (map == null)
				creator();

			map.put(key, value);

			return this;

		}

		public MapBuilder<K, V> remove(K key) {

			if (map == null)
				return this;

			if (map.containsKey(key))
				map.remove(key);

			return this;

		}

		public MapBuilder<K, V> clear() {
			if (map != null)
				map.clear();
			return this;

		}

		public Map<K, V> builder() {

			return map;

		}

		private Map<K, V> map = new HashMap<K, V>();
	}

}
