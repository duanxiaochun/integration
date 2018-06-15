package cn.com.inlee.common.exception;

/**
 * 认证异常
 * 
 * @author dxcb
 *
 */
@SuppressWarnings("serial")
public class ParamException extends BaseException {

	public ParamException() {
		super(PARAM_ERR, "参数错误或为空", "参数错误或为空");
	}

	public ParamException(String tips) {
		super(PARAM_ERR, "参数错误或为空", tips);
	}

	public ParamException(String tips, BaseException original) {
		super(PARAM_ERR, tips, original);

	}

}
