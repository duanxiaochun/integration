package cn.com.inlee.common.exception;

/**
 * 
 * @author dxcb
 *
 */
@SuppressWarnings("serial")
public class FileOperationException extends BaseException {

	public FileOperationException() {
		this("文件操作异常", "文件操作错误");
	}

	public FileOperationException(String tips) {
		this("文件操作异常：" + tips, tips);
	}

	public FileOperationException(String message,String tips) {
		super(FILE_OPERATION, message, tips);
	}

	public FileOperationException(String tips, Exception original) {
		super(FILE_OPERATION, tips, original);

	}

}
