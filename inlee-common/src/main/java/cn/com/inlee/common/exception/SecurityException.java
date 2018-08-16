package cn.com.inlee.common.exception;

/**
 * 
 * @author dxcb
 *
 */
@SuppressWarnings("serial")
public class SecurityException extends BaseException {

	public SecurityException() {
		this("失败");
	}

	public SecurityException(String tips) {
		this(tips, null);
	}

	public SecurityException(String tips, Exception original) {
		super(ERR_SECURITY, tips, original);

	}

}
