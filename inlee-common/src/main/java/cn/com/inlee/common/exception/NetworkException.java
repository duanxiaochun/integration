package cn.com.inlee.common.exception;

/**
 * 
 * @author dxcb
 *
 */
@SuppressWarnings("serial")
public class NetworkException extends BaseException {

	public NetworkException() {
		this("网络异常", "访问接口异常");
	}

	public NetworkException(String tips) {
		this("网络异常", tips);
	}

	public NetworkException(String message, String tips) {
		super(NET_ERROR, message, tips);
	}

	public NetworkException(String tips, Exception original) {
		super(NET_ERROR, tips, original);

	}

}
