package cn.com.inlee.mina.entity;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@SuppressWarnings("serial")
@Getter
@Setter
@ToString
public class SessionPacket implements Serializable {

	private Long sessionId;

	private Packet packet;

	public SessionPacket() {
	}

	public SessionPacket(Long sessionId, Packet packet) {
		this.sessionId = sessionId;
		this.packet = packet;

	}

}
