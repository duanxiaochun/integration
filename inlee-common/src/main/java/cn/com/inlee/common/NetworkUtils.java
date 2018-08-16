package cn.com.inlee.common;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpState;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

import cn.com.inlee.common.exception.BaseException;
import cn.com.inlee.common.exception.InnerAPIException;

public class NetworkUtils {

	private static String TRANS_ENCODING = "utf-8";
	private static String TRANS_CONTENT_TYPE = "application/json";

	public static class Request {

		private HttpClient httpClient;
		private Cookie[] cookies;
		private String url;
		private Map<String, String> params;
		private String jsonString;
		private String charSet;

		public String get() throws IOException {
			return NetworkUtils.get(this);
		}

		public <T> T get(Class<T> clazz) throws InnerAPIException {
			return NetworkUtils.get(this, clazz);
		}

		public String post() throws IOException {
			return NetworkUtils.post(this);
		}

		public byte[] postByte() throws IOException {
			return NetworkUtils.postBuffer(this);
		}

		public <T> T post(Class<T> clazz) throws InnerAPIException {
			try {
				return NetworkUtils.post(this, clazz);
			}
			catch (BaseException e) {
				// TODO Auto-generated catch block
				throw new InnerAPIException(e.getMessage(), e.getTips());
			}
		}

		public Request() {

		}

		public Request(HttpClient httpClient, String url, Map<String, String> params, String charset) {
			this(httpClient, null, url, params, charset);
		}

		public Request(HttpClient httpClient, String url, Map<String, String> params) {
			this(httpClient, null, url, params, TRANS_ENCODING);
		}

		public Request(HttpClient httpClient, Cookie[] cookies, String url, Map<String, String> params) {
			this(httpClient, cookies, url, params, TRANS_ENCODING);
		}

		public Request(Cookie[] cookies, String url, Map<String, String> params) {
			this(null, cookies, url, params, TRANS_ENCODING);
		}

		public Request(String url) {
			this(url, null);
		}

		public Request(String url, Map<String, String> params) {
			this(null, null, url, params, TRANS_ENCODING);
		}

		public <T> Request(String url, T t) {
			this(null, null, url, JsonUtils.to(t), TRANS_ENCODING);
		}

		public Request(HttpClient httpClient, Cookie[] cookies, String url, Map<String, String> params,
				String charSet) {
			this.httpClient = httpClient;
			this.cookies = cookies;
			this.url = url;
			this.params = params;
			this.charSet = charSet;
		}

		public Request(HttpClient httpClient, Cookie[] cookies, String url, String jsonString, String charSet) {
			this.httpClient = httpClient;
			this.cookies = cookies;
			this.url = url;
			this.setJsonString(jsonString);
			this.charSet = charSet;
		}

		public HttpClient getHttpClient() {
			return httpClient;
		}

		public void setHttpClient(HttpClient httpClient) {
			this.httpClient = httpClient;
		}

		public Cookie[] getCookies() {
			return cookies;
		}

		public void setCookies(Cookie[] cookies) {
			this.cookies = cookies;
		}

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}

		public Map<String, String> getParams() {
			return params;
		}

		public void setParams(Map<String, String> params) {
			this.params = params;
		}

		public String getCharSet() {
			return charSet;
		}

		public void setCharSet(String charSet) {
			this.charSet = charSet;
		}

		public String getJsonString() {
			return jsonString;
		}

		public void setJsonString(String jsonString) {
			this.jsonString = jsonString;
		}

	}

	private static String get(Request request) throws IOException {

		final HttpClient httpClient = request.getHttpClient() == null ? new HttpClient() : request.getHttpClient();

		byte[] responseBody = null;
		String param = mergeQueryParams(request.getParams());
		GetMethod methodGet = new GetMethod(
				(null != param && param.length() > 0) ? request.getUrl() + '?' + param : request.getUrl());

		if (request.getCookies() != null) {
			methodGet.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, request.getCharSet());
			HttpState state = new HttpState();
			state.addCookies(request.getCookies());
			httpClient.setState(state);
		}

		int statusCode = httpClient.executeMethod(methodGet);

		if (isRedirect(statusCode)) {
			httpClient.executeMethod(methodGet);
			responseBody = methodGet.getResponseBody();
		}
		else {
			responseBody = methodGet.getResponseBody();
		}

		if (methodGet != null)
			methodGet.releaseConnection();

		return new String(responseBody, request.getCharSet());
	}

	private static <T> T get(Request request, Class<T> clazz) throws InnerAPIException {

		final HttpClient httpClient = request.getHttpClient() == null ? new HttpClient() : request.getHttpClient();

		byte[] responseBody = null;
		String param = mergeQueryParams(request.getParams());
		GetMethod methodGet = new GetMethod(
				(null != param && param.length() > 0) ? request.getUrl() + '?' + param : request.getUrl());

		if (request.getCookies() != null) {
			methodGet.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, request.getCharSet());
			HttpState state = new HttpState();
			state.addCookies(request.getCookies());
			httpClient.setState(state);
		}

		int statusCode;
		try {
			statusCode = httpClient.executeMethod(methodGet);

			if (isRedirect(statusCode)) {
				httpClient.executeMethod(methodGet);
				responseBody = methodGet.getResponseBody();
			}
			else {
				responseBody = methodGet.getResponseBody();
			}

			return JsonUtils.from(new String(responseBody, request.getCharSet()), clazz);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new InnerAPIException(e.getMessage(), "调用服务异常");
		} finally {
			if (methodGet != null)
				methodGet.releaseConnection();
		}
	}

	@SuppressWarnings("deprecation")
	private static String post(Request request) throws IOException {

		final HttpClient httpClient = request.getHttpClient() == null ? new HttpClient() : request.getHttpClient();

		String result = "";

		PostMethod post = new PostMethod(request.getUrl());
		post.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, request.getCharSet());
		if (StringUtils.isNotEmpty(request.getParams())) {
			post.setRequestBody(mergePostParams(request.getParams()));
		}
		else if (StringUtils.isNotEmpty(request.getJsonString())) {
			// InputStream inStream = new
			// ByteInputStream(request.getJsonString().getBytes(request.getCharSet()),
			// request.getJsonString().getBytes(request.getCharSet()).length);
			post.getParams().setParameter("Content-type", TRANS_CONTENT_TYPE);
			post.setRequestBody(request.getJsonString());
		}
		if (request.getCookies() != null) {
			HttpState state = new HttpState();
			state.addCookies(request.getCookies());
			httpClient.setState(state);
		}
		int statusCode = httpClient.executeMethod(post);
		if (isRedirect(statusCode)) {
			Header header = post.getResponseHeader("location");
			String location = header.getValue();
			if (location == null || location.equals("")) {
				location = "/";
			}
			GetMethod getMethod = new GetMethod(location);
			httpClient.executeMethod(getMethod);
			result = new String(getMethod.getResponseBody(), request.getCharSet());
			getMethod.releaseConnection();
		}
		else {
			result = new String(post.getResponseBody(), request.getCharSet());
			post.releaseConnection();
		}

		return result;

	}

	@SuppressWarnings("deprecation")
	private static byte[] postBuffer(Request request) throws IOException {

		final HttpClient httpClient = request.getHttpClient() == null ? new HttpClient() : request.getHttpClient();

		byte[] buffer = null;
		PostMethod post = new PostMethod(request.getUrl());
		post.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, request.getCharSet());
		if (StringUtils.isNotEmpty(request.getParams())) {
			post.setRequestBody(mergePostParams(request.getParams()));
		}
		else if (StringUtils.isNotEmpty(request.getJsonString())) {
			// InputStream inStream = new
			// ByteInputStream(request.getJsonString().getBytes(request.getCharSet()),
			// request.getJsonString().getBytes(request.getCharSet()).length);
			post.getParams().setParameter("Content-type", TRANS_CONTENT_TYPE);
			post.setRequestBody(request.getJsonString());
		}
		if (request.getCookies() != null) {
			HttpState state = new HttpState();
			state.addCookies(request.getCookies());
			httpClient.setState(state);
		}
		int statusCode = httpClient.executeMethod(post);
		if (isRedirect(statusCode)) {
			Header header = post.getResponseHeader("location");
			String location = header.getValue();
			if (location == null || location.equals("")) {
				location = "/";
			}
			GetMethod getMethod = new GetMethod(location);
			httpClient.executeMethod(getMethod);
			buffer = getMethod.getResponseBody();
			getMethod.releaseConnection();
		}
		else {
			buffer = post.getResponseBody();
			post.releaseConnection();
		}

		return buffer;

	}

	@SuppressWarnings("deprecation")
	private static <T> T post(Request request, Class<T> clazz) throws BaseException {

		final HttpClient httpClient = request.getHttpClient() == null ? new HttpClient() : request.getHttpClient();

		T result = null;

		PostMethod post = new PostMethod(request.getUrl());
		post.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, request.getCharSet());
		if (StringUtils.isNotEmpty(request.getParams())) {
			post.setRequestBody(mergePostParams(request.getParams()));
		}
		else if (StringUtils.isNotEmpty(request.getJsonString())) {
			post.getParams().setParameter("Content-type", TRANS_CONTENT_TYPE);
			post.setRequestBody(request.getJsonString());
		}
		if (request.getCookies() != null) {
			HttpState state = new HttpState();
			state.addCookies(request.getCookies());
			httpClient.setState(state);
		}
		try {
			int statusCode = httpClient.executeMethod(post);
			if (isRedirect(statusCode)) {
				Header header = post.getResponseHeader("location");
				String location = header.getValue();
				if (location == null || location.equals("")) {
					location = "/";
				}
				GetMethod get = new GetMethod(location);
				httpClient.executeMethod(get);
				result = JsonUtils.from(new String(get.getResponseBody(), request.getCharSet()), clazz);
				get.releaseConnection();
			}
			else {
				result = JsonUtils.from(new String(post.getResponseBody(), request.getCharSet()), clazz);
				post.releaseConnection();
			}
		}
		catch (IOException e) {
			throw new InnerAPIException(e.getMessage(), "调用服务异常");
		}

		return result;

	}

	private static boolean isRedirect(int statusCode) {
		if (statusCode == HttpStatus.SC_MOVED_PERMANENTLY || statusCode == HttpStatus.SC_MOVED_TEMPORARILY
				|| statusCode == HttpStatus.SC_SEE_OTHER || statusCode == HttpStatus.SC_TEMPORARY_REDIRECT)
			return true;
		return false;
	}

	public static HashMap<String, String> toHashMap(String str) {
		if (str == null || str == "")
			return null;

		HashMap<String, String> map = new HashMap<String, String>();
		String[] res = str.split("&");
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

	public static String mergeQueryParams(Map<String, String> params) {

		if (params == null)
			return "";

		StringBuilder builder = new StringBuilder(50);
		if (null != params && params.size() > 0) {
			String key;
			String value;
			boolean first = true;
			for (Map.Entry<String, String> entry : params.entrySet()) {
				if (first == false) {
					builder.append('&');
				}
				else {
					first = false;
				}
				key = entry.getKey();
				value = entry.getValue();
				builder.append(key);
				builder.append('=');
				builder.append(value);
			}
		}
		return builder.toString();
	}

	public static NameValuePair[] mergePostParams(Map<String, String> params) {

		NameValuePair[] nvps = new NameValuePair[params.size()];

		int index = 0;
		for (Map.Entry<String, String> entry : params.entrySet()) {
			nvps[index++] = new NameValuePair(entry.getKey(), entry.getValue());
		}
		return nvps;
	}

}
