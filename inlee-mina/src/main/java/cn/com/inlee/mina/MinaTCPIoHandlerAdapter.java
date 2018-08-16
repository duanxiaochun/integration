package cn.com.inlee.mina;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.com.inlee.mina.entity.Packet;
import cn.com.inlee.mina.entity.SessionPacket;
import cn.com.inlee.mina.receive.MessageProcessor;
import cn.com.inlee.mina.session.SessionCache;
import lombok.extern.slf4j.Slf4j;

@Component("ioHandlerAdapter")
@Slf4j
public class MinaTCPIoHandlerAdapter extends IoHandlerAdapter {

	@Autowired
	private MessageProcessor messageProcessor;

	@Autowired
	private SessionCache sessionCache;

	@Override
	public void messageReceived(IoSession session, Object message) throws Exception {

		messageProcessor.receive(new SessionPacket(session.getId(), (Packet) message));

		log.info("sessionId:{},message:{}", session.getId(), message.toString());

	}

	@Override
	public void exceptionCaught(IoSession session, Throwable cause) throws Exception {

		log.debug("[{}]异常:{}", session.getId(), cause.getMessage());
		disconnect(session);
	}

	@Override
	public void sessionClosed(IoSession session) throws Exception {
		log.debug("[{}]会话关闭", session.getId());
		disconnect(session);
	}

	@Override
	public void sessionCreated(IoSession session) throws Exception {
		log.debug("[{}]会话创建", session.getId());
		session.getConfig().setIdleTime(IdleStatus.BOTH_IDLE, 30000);

	}

	@Override
	public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
		log.debug("[{}]会话休眠", session.getId());
	}

	@Override
	public void sessionOpened(IoSession session) throws Exception {

		log.debug("[{}]会话打开", session.getId());

		sessionCache.save(session);
	}

	@SuppressWarnings("deprecation")
	private void disconnect(IoSession session) {

		if (session.isConnected()) {
			session.close(true);
		}

		sessionCache.remove(session.getId());
	}

}