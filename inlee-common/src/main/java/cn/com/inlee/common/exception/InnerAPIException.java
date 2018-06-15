package cn.com.inlee.common.exception;

/**
 * 
 * @author dxcb
 *
 */
@SuppressWarnings("serial")
public class InnerAPIException extends BaseException {

	public InnerAPIException() {
		this("访问接口异常", "访问接口异常");
	}

	public InnerAPIException(String tips) {
		this("访问接口异常", tips);
	}

	public InnerAPIException(String message, String tips) {
		super(THIRD_API, message, tips);
	}

	public InnerAPIException(String message, String tips, Exception original) {
		super(THIRD_API, tips, original);

	}

}
