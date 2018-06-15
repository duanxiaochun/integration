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
public class NullParameterException extends BaseException {
 
	public NullParameterException() {
		this("信息不能为空", "信息不能为空");
	}

	public NullParameterException(String tips) {
		this("信息已经存在", tips);
	}

	public NullParameterException(String message, String tips) {
		super(DATA_NULL, message, tips);
	}

}
