package cn.com.inlee.mina.session;

import org.apache.mina.core.session.IoSession;

public interface SessionCache {

	/**
	 * 缓存Session
	 * 
	 * @param session
	 */
	public void save(IoSession session);

	/**
	 * 查询Session
	 * 
	 * @param sessionId
	 * @return
	 */
	public IoSession find(Long sessionId);

	/**
	 * Session 存在并且可用
	 * 
	 * @param name
	 * @return
	 */
	public boolean existAndUsable(Long sessionId);

   
	/**
	 * 通过name删除Session
	 * 
	 * @param name
	 */
	public void remove(Long sessionId);
}
