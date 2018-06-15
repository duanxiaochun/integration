package cn.com.inlee.common.exception;

/**
 * 
 * @author dxcb
 *
 */
@SuppressWarnings("serial")
public class VerificationException extends BaseException {

	public VerificationException() {
		this("数据验证失败", null);
	}

	public VerificationException(String tips) {
		this(tips, null);
	}

	public VerificationException(String tips, Exception original) {
		super(ERROR_VERIFICATION, tips, original);

	}

}
