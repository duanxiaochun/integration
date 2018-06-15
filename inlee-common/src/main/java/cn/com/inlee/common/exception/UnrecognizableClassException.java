package cn.com.inlee.common.exception;

/**
 * 
 * @author dxcb
 *
 */
@SuppressWarnings("serial")
public class UnrecognizableClassException extends BaseException {

	public UnrecognizableClassException() {
		this("类型错误");
	}

	public UnrecognizableClassException(String tips) {
		super(UNRECOGNIABLE_CALSS, "无法识别的类型", tips);
	}

	public UnrecognizableClassException(String message, String tips) {
		super(UNRECOGNIABLE_CALSS, "无法识别的类型：" + message, tips);
	}

	public UnrecognizableClassException(String tips, Exception original) {
		super(UNRECOGNIABLE_CALSS, tips, original);

	}

}
