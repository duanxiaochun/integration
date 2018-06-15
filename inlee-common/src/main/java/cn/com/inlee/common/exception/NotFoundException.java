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
public class NotFoundException extends BaseException {

	public NotFoundException() {
		super(DATA_NOT_FONUD, "未找到相关的数据", "未找到相关的数据");
	}

	public NotFoundException(String tips) {
		this("未找到相关的数据", tips);
	}

	public NotFoundException(String message, String tips) {
		super(DATA_NOT_FONUD, "未找到相关的数据:" + message, tips);
	}

}
