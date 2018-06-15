package cn.com.inlee.common.exception;

/**
 * 
 * @author dxcb
 *
 */
@SuppressWarnings("serial")
public class RedirectException extends BaseException {

	private String redirect;

	public RedirectException() {
		this("重定向地址错误", "重定向地址错误", "");
	}

	public RedirectException(String tips, String redirect) {
		this("重定向地址错误", tips, redirect);
	}

	public RedirectException(String message, String tips, String redirect) {
		super(NET_REDIRECT, message, tips);
		setRedirect(redirect);
	}

	public String getRedirect() {
		return redirect;
	}

	public void setRedirect(String redirect) {
		this.redirect = redirect;
	}

}
