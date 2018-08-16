package cn.com.inlee.authority;

import java.util.List;

import cn.com.inlee.common.exception.BaseException;

public interface UserPermissionService {

	public List<String> getRoles(String username) throws BaseException;

	public List<String> getPermissions(String username) throws BaseException;

}
