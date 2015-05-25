package com.princeli.handler;

import org.apache.mina.core.session.IoSession;

import com.princeli.common.BinaryEndpoint;
import com.princeli.exception.MimaMessageException;
import com.princeli.message.BinaryMessage;
import com.princeli.message.SocketRequestMessage;
import com.princeli.message.SocketResponseMessage;


/**
 * 业务处理Handler
 * 根据相应的交易类型，查找对应的交易方法
 * @author miller
 *
 */
public interface SocketBusinessMessageHandler {
	
	public final static String SOCKETPORTTEST = "0000" ;
	
	public SocketResponseMessage socketTest(BinaryMessage binaryMessage , BinaryEndpoint binaryEndpoint , IoSession session) throws MimaMessageException ;
	
	public SocketResponseMessage messageReceived(SocketRequestMessage socketRequestMessage , IoSession session) throws MimaMessageException ;   
}
