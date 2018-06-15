package cn.com.inlee.common.exception;

/**
 * 
 * @author dxcb
 *
 */
@SuppressWarnings("serial")
public class ThirdAPIException extends BaseException {

	public ThirdAPIException() {
		this("访问接口异常", "访问接口异常");
	}

	public ThirdAPIException(String tips) {
		this("访问接口异常", tips);
	}

	public ThirdAPIException(String message, String tips) {
		super(THIRD_API, message, tips);
	}

	public ThirdAPIException(String tips, Exception original) {
		super(THIRD_API, tips, original);

	}

}
