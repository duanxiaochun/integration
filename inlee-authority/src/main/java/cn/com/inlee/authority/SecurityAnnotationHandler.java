package cn.com.inlee.authority;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import cn.com.inlee.authority.annotation.RequiredPermission;
import cn.com.inlee.common.exception.AuthenticationException;
import cn.com.inlee.common.exception.BaseException;
import cn.com.inlee.common.exception.PermissionException;

public class SecurityAnnotationHandler implements HandlerInterceptor {

	@Autowired
	private UserPermissionService userPermissionService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		return true;
		// Object user =
		// request.getSession().getAttribute(SecurityUtils.SESSION_USER_KEY);
		// if (user == null)
		// throw new AuthenticationException("用户未登录");//
		// response.sendError(HttpStatus.FORBIDDEN.value(),
		// // "用户未登录");
		//
		// // 验证权限
		// if (hasPermission(user.toString(), handler)) {
		// return true;
		// }
		// // null == request.getHeader("x-requested-with") TODO
		// 暂时用这个来判断是否为ajax请求
		// // 如果没有权限 则抛403异常 springboot会处理，跳转到 /error/403 页面
		// // response.sendError(HttpStatus.FORBIDDEN.value(), "无权限");
		// throw new PermissionException("无权限");

		// return false;
	}

	/**
	 * 是否有权限
	 *
	 * @param handler
	 * @return
	 * @throws BaseException
	 */
	private boolean hasPermission(String username, Object handler) throws BaseException {
		if (handler instanceof HandlerMethod) {
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			// 获取方法上的注解
			RequiredPermission requiredPermission = handlerMethod.getMethod().getAnnotation(RequiredPermission.class);
			// 如果方法上的注解为空 则获取类的注解
			if (requiredPermission == null) {
				requiredPermission = handlerMethod.getMethod().getDeclaringClass()
						.getAnnotation(RequiredPermission.class);
			}
			// 如果标记了注解，则判断权限
			if (requiredPermission != null && !StringUtils.isEmpty(requiredPermission.value())) {
				// redis或数据库 中获取该用户的权限信息 并判断是否有权限
				List<String> permissions = userPermissionService.getPermissions(username);
				if (CollectionUtils.isEmpty(permissions)) {
					return false;
				}

				for (String v : requiredPermission.value()) {
					if (permissions.contains(v)) {
						return true;
					}
				}

				return false;

			}
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO
	}
}