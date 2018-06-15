package cn.com.inlee.common;

import java.io.Serializable;

import cn.com.inlee.common.JsonUtils;
import cn.com.inlee.common.exception.BaseException;

@SuppressWarnings("serial")
public class ClientResponse implements Serializable {

	public static boolean isSuccess(String code) {
		return BaseException.SUCCESS.equals(code);
	}

	private String code;
	private String msg;
	private Object data;

	public static Response newInstance() {
		return new Response();
	}

	public static class Response {

		private ClientResponse response = new ClientResponse();

		public Response error(String code, String message) {
			setAll(code, message, null);
			return this;
		}

		public Response ok() {
			setAll(BaseException.SUCCESS, "操作成功", null);
			return this;
		}

		public Response ok(String message) {
			setAll(BaseException.SUCCESS, message, null);
			return this;
		}

		public Response ok(Object data) {
			setAll(BaseException.SUCCESS, "操作成功", data);
			return this;
		}

		public Response ok(String message, Object data) {
			setAll(BaseException.SUCCESS, message, data);
			return this;
		}

		public ClientResponse builder() {
			return response;
		}

		private void setAll(String code, String message, Object data) {
			response.setMsg(message);
			response.setCode(code);
			response.setData(data);
		}
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return String.format("code:%s ; message:%s ; data:%s ", code, msg, data != null ? JsonUtils.to(data) : "");
	}

}
