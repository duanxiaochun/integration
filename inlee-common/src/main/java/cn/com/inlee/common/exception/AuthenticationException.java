package cn.com.inlee.common.exception;

/**
 * 认证异常
 * 
 * @author dxcb
 *
 */
@SuppressWarnings("serial")
public class AuthenticationException extends BaseException {

	public AuthenticationException() {
		super(ERROR_AUTH, "认证失败", "认证失败");
	}

	public AuthenticationException(String tips) {
		super(ERROR_AUTH, "认证失败", tips);
	}

	public AuthenticationException(String tips, Exception original) {
		super(ERROR_AUTH, tips, original);

	}

}
