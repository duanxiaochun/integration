package cn.com.inlee.mina.entity;

import cn.com.inlee.common.exception.BaseException;

public interface Packet {

	/**
	 * 解析字节流
	 * 
	 * @param buffer
	 * @throws BaseException
	 */
	void fromBuffer(byte[] buffer) throws BaseException;

	/**
	 * 获取字节流
	 * 
	 * @return
	 */
	byte[] getBuffer() throws BaseException;

	/**
	 * 包Id
	 * 
	 * @return
	 */
	String getMessageId();

	/**
	 * 设置包Id
	 * 
	 * @return
	 */
	String setMessageId(String messageId);
}
