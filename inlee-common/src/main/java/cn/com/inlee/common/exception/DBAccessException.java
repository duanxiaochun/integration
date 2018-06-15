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
public class DBAccessException extends BaseException {

	public DBAccessException() {
		super(DB_OPRATOR, "数据库访问异常", "数据访问失败");
	}

	public DBAccessException(String tips) {

		this("数据库访问异常：" + tips, tips);
	}

	public DBAccessException(String message, String tips) {
		super(DB_OPRATOR, message, tips);
	}

}
