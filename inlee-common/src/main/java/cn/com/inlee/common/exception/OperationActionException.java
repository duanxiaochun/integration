package cn.com.inlee.common.exception;

/**
 * @author 作者 jesse E-mail: 87392304@qq.com
 * @date 创建时间：2016-11-16 下午6:53:18
 * @version 1.0
 * @parameter
 * @since
 * @return
 */
@SuppressWarnings("serial")
public class OperationActionException extends BaseException {

	public OperationActionException() {
		super("不允许此动作");
	}

	public OperationActionException(String tips) {
		this(tips, null);
	}

	public OperationActionException(String tips, Exception original) {
		super(DISALLOW_OPERATION, tips, original);
	}

}
