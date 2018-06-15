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
public class ExpiredException extends BaseException {

	public ExpiredException() {
		super(ERROR_EXPIRED, "请求过期", "请求过期.");
	}

	public ExpiredException(String tips) {
		this("请求过期." + tips, tips);
	}

	public ExpiredException(String message, String tips) {
		super(ERROR_EXPIRED, message, tips);
	}

}
