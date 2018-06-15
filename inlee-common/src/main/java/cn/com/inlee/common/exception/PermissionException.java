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
public class PermissionException extends BaseException {

	public PermissionException() {
		this("没有权限访问", "没有权限访问");
	}

	public PermissionException(String tips) {
		this("没有权限访问：" + tips, tips);
	}

	public PermissionException(String message, String tips) {
		super(NO_ACCESS, "没有权限访问:" + message, tips);
	}

}
