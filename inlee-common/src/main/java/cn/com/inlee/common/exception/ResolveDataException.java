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
public class ResolveDataException extends BaseException {

	public ResolveDataException() {
		this("数据解析错误", "数据解析错误");
	}

	public ResolveDataException(String tips) {
		this("数据解析错误：" + tips, tips);
	}

	public ResolveDataException(String message, String tips) {
		super(DATA_RESOLVE, "数据解析错误:" + message, tips);
	}

}
