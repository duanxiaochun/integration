package cn.com.inlee.mina.session;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.mina.core.session.IoSession;

public class MemorySession implements SessionCache {

	private Map<Long, IoSession> sessions = new ConcurrentHashMap<Long, IoSession>();

	@Override
	public void save(IoSession session) {
		remove(session.getId());
		sessions.put(session.getId(), session);
	}

	@Override
	public IoSession find(Long sessionId) {
		if (sessions.containsKey(sessionId)) {
			return sessions.get(sessionId);
		}
		return null;
	}

	@Override
	public boolean existAndUsable(Long sessionId) {

		return sessions.containsKey(sessionId) && sessions.get(sessionId).isConnected();
	}

	@Override
	public void remove(Long sessionId) {
		if (sessions.containsKey(sessionId))
			sessions.remove(sessionId);
	}

}
