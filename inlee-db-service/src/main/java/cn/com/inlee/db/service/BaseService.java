package cn.com.inlee.db.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
 
import cn.com.inlee.common.exception.DBAccessException;
 

public interface BaseService<T extends Object, PK extends Serializable> {
	/**
	 * 添加
	 * 
	 * @param t
	 * @return
	 */
	PK create(T t) throws DBAccessException;

	/**
	 * 批量增加
	 * 
	 * @param e
	 * @throws Exception
	 */
	void batchCreate(Collection<T> e) throws DBAccessException;

	/**
	 * 修改
	 * 
	 * @param t
	 */
	Long modify(T t) throws DBAccessException;

	 
	/**
	 * 删除
	 * 
	 * @throws DataAccessException
	 */

	Long remove(PK pk) throws DBAccessException;

	/**
	 * 删除
	 * 
	 * @throws DataAccessException
	 */

	@SuppressWarnings("unchecked")
	void remove(PK... pks) throws DBAccessException;

	/**
	 * 根据主键查询
	 * 
	 * @param pk
	 * @return DataAccessException
	 */
	T searchById(PK pk) throws DBAccessException;

	/**
	 * 
	 * @return
	 * @throws DataAccessException
	 */
	List<T> searchAll() throws DBAccessException;
}