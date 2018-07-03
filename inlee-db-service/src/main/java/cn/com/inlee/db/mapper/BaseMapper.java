package cn.com.inlee.db.mapper;

import java.io.Serializable;
import java.util.List;

import org.springframework.dao.DataAccessException;
 
 
public interface BaseMapper<T, PK extends Serializable> {

	/**
	 * Saves a given entity. Use the returned instance for further operations as
	 * the save operation might have changed the entity instance completely.
	 * 
	 * @param entity
	 * @return the saved entity
	 */
	<S extends T> PK save(S entity) throws DataAccessException;

	/**
	 * Saves all given entities.
	 * 
	 * @param entities
	 * @return the saved entities
	 * @throws IllegalArgumentException
	 *             in case the given entity is {@literal null}.
	 */
	<S extends T> PK saveBatch(Iterable<S> entities) throws DataAccessException;

	/**
	 * Saves a given entity. Use the returned instance for further operations as
	 * the save operation might have changed the entity instance completely.
	 * 
	 * @param entity
	 * @return the saved entity
	 */
	<S extends T> Long update(S entity) throws DataAccessException;
	
 

	/**
	 * Deletes the entity with the given id.
	 * 
	 * @param id
	 *            must not be {@literal null}.
	 * @throws IllegalArgumentException
	 *             in case the given {@code id} is {@literal null}
	 */
	Long deleteByPK(PK pk) throws DataAccessException;

	/**
	 * Deletes the entity with the given id.
	 * 
	 * @param id
	 *            must not be {@literal null}.
	 * @throws IllegalArgumentException
	 *             in case the given {@code id} is {@literal null}
	 */
	Long deleteByPKs(@SuppressWarnings("unchecked") PK... pks) throws DataAccessException;

	/**
	 * Deletes a given entity.
	 * 
	 * @param entity
	 * @throws IllegalArgumentException
	 *             in case the given entity is {@literal null}.
	 */
	Long delete(T entity) throws DataAccessException;

	/**
	 * Returns whether an entity with the given id exists.
	 * 
	 * @param id
	 *            must not be {@literal null}.
	 * @return true if an entity with the given id exists, {@literal false}
	 *         otherwise
	 * @throws IllegalArgumentException
	 *             if {@code id} is {@literal null}
	 */
	boolean exists(PK pk) throws DataAccessException;

	/**
	 * Retrieves an entity by its id.
	 * 
	 * @param id
	 *            must not be {@literal null}.
	 * @return the entity with the given id or {@literal null} if none found
	 * @throws IllegalArgumentException
	 *             if {@code id} is {@literal null}
	 */
	T selectOne(PK pk) throws DataAccessException;

	/**
	 * Returns all instances of the type.
	 * 
	 * @return all entities
	 */
	List<T> selectAll() throws DataAccessException;

	/**
	 * Returns all instances of the type with the given IDs.
	 * 
	 * @param ids
	 * @return
	 */
	List<T> selectByPKs(Iterable<PK> pks) throws DataAccessException;

	 
}
