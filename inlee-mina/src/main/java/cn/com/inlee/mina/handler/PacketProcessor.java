package cn.com.inlee.mina.handler;

import cn.com.inlee.mina.entity.SessionPacket;

public interface PacketProcessor {

	void processor(SessionPacket packet); 

}
