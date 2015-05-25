package com.princeli.handler;

import org.apache.mina.core.future.IoFutureListener;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

import com.princeli.message.HttpRequestMessage;
import com.princeli.message.HttpResponseMessage;

public class ProtocolHandler extends IoHandlerAdapter {
	
	private BusinessMessageHandler businessMessageHandler; 


	public BusinessMessageHandler getBusinessMessageHandler() {
		return businessMessageHandler;
	}

	public void setBusinessMessageHandler(
			BusinessMessageHandler businessMessageHandler) {
		this.businessMessageHandler = businessMessageHandler;
	}

	@Override
	public void sessionCreated(IoSession session) throws Exception {
		super.sessionCreated(session);
	}
	
	@Override
	public void sessionOpened(IoSession session) {
		// set idle time to 60 seconds
		session.getConfig().setIdleTime(IdleStatus.BOTH_IDLE, 60);
	}

	@Override
	public void messageReceived(IoSession session, Object message) {
		HttpRequestMessage request = (HttpRequestMessage) message;
		HttpResponseMessage response = businessMessageHandler.messageReceived(request) ;
		if (response != null) {
			session.write(response).addListener(IoFutureListener.CLOSE);
		}
	}

	
	@Override
	public void sessionIdle(IoSession session, IdleStatus status) {
		session.close(false);
	}

	@Override
	public void exceptionCaught(IoSession session, Throwable cause) {
		session.close(false);
	}
}
