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
public class ExistsException extends BaseException {
 
	public ExistsException() {
		super(DATA_EXISTS, "信息已经存在", "信息已经存在");
	}

	public ExistsException(String tips) {
		this("信息已经存在", tips);
	}

	public ExistsException(String message, String tips) {
		super(DATA_EXISTS, message, tips);
	}

}
