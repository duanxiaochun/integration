package cn.com.inlee.mina.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;

import cn.com.inlee.common.StringUtils;
import cn.com.inlee.mina.entity.Packet;
import cn.com.inlee.mina.entity.SessionPacket;
import cn.com.inlee.mina.session.SessionCache;

public abstract class AbstractPacketProcessor implements PacketProcessor {

	@Autowired
	private SessionCache sessionCache;

	@Async("taskExecutor")
	@Override
	public final void processor(SessionPacket request) {

		request.getPacket().setMessageId(request.getSessionId() + "-" + StringUtils.getUUIDNoSeparator());
		Packet response = process(request.getPacket());
		if (sessionCache.existAndUsable(request.getSessionId())) {
			sessionCache.find(request.getSessionId()).write(response);
			notify(request.getPacket(), 0);
		}
		else {
			notify(request.getPacket(), 1);
		}

	}

	/**
	 * 现场处理报文
	 * 
	 * @param request
	 * @return
	 */
	public abstract Packet process(Packet request);

	/**
	 * 处理结果通知
	 * 
	 * @param request
	 * @param result
	 */
	public abstract void notify(Packet request, int result);

}
