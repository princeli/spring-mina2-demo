package com.princeli.handler.impl;

import org.apache.mina.core.session.IoSession;

import com.princeli.common.BinaryEndpoint;
import com.princeli.exception.MimaMessageException;
import com.princeli.handler.MessageInvocation;
import com.princeli.handler.SocketAbstractMessageHandler;
import com.princeli.handler.SocketBusinessMessageHandler;
import com.princeli.message.BinaryMessage;
import com.princeli.message.SocketRequestMessage;
import com.princeli.message.SocketResponseMessage;

public class SocketBusinessMessageHandlerImpl extends SocketAbstractMessageHandler implements SocketBusinessMessageHandler {

	@Override
	public SocketResponseMessage messageReceived(SocketRequestMessage socketRequestMessage, IoSession session) throws MimaMessageException {
		try {
			BinaryEndpoint binaryEndpoint = new BinaryEndpoint(session) ;
			BinaryMessage fromHandset = new BinaryMessage(socketRequestMessage.getLength() ,socketRequestMessage.getTradeCode(),socketRequestMessage.getContent());
			return process(fromHandset, binaryEndpoint, session); 
		} catch (Exception e) {
			e.printStackTrace();
			throw new MimaMessageException(e.getMessage()) ;
		}
	}

	@Override
	@MessageInvocation(SOCKETPORTTEST)
	public SocketResponseMessage socketTest(BinaryMessage binaryMessage,
			BinaryEndpoint binaryEndpoint, IoSession session)
			throws MimaMessageException {
		SocketResponseMessage message = new SocketResponseMessage() ;
		message.setLength(18) ;
		message.setTradeCode("0000") ;
		message.setContent("Socket 服务正常".getBytes()) ;
		message.setReturnCode("000000".getBytes()) ; 
		return message ;
	}

}
