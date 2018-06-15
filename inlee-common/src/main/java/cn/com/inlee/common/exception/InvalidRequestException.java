package cn.com.inlee.common.exception;

import cn.com.inlee.common.exception.BaseException;

/**
 * @author 作者 jesse E-mail: 87392304@qq.com
 * @date 创建时间：2016-11-16 下午6:53:18
 * @version 1.0
 * @parameter
 * @since
 * @return
 */
@SuppressWarnings("serial")
public class InvalidRequestException extends BaseException {

	public InvalidRequestException() {
		super(INVALID_REQUEST, "请求无效", "参数无效");
	}

	public InvalidRequestException(String tips) {
		this("请求无效", tips);
	}

	public InvalidRequestException(String message, String tips) {
		super(INVALID_REQUEST, message, tips);
	}

}
