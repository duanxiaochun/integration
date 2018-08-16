package cn.com.inlee.mina.receive;

import cn.com.inlee.mina.entity.SessionPacket;

public interface MessageProcessor {

	/**
	 * 接收客户端的信息
	 * 
	 * @param message
	 *            接收的数据 
	 * @throws Exception
	 */
	void receive(SessionPacket request);

	
}
