package cn.com.inlee.mina.receive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.com.inlee.mina.entity.SessionPacket;
import cn.com.inlee.mina.handler.PacketProcessor;

@Component("messageProcessor")
public class OriginalMessageProcessor implements MessageProcessor {

	@Autowired
	private PacketProcessor packetProcessor;

	public void processor(SessionPacket packet) {

		packetProcessor.processor(packet);
	}

	/**
	 * 处理Session缓存 
	 * 处理登录问题
	 */
	@Override
	public void receive(SessionPacket request) {
		//
		processor(request);
	}
}
