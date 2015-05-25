package com.princeli.handler;

import org.apache.mina.core.future.IoFutureListener;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

import com.princeli.message.SocketRequestMessage;
import com.princeli.message.SocketResponseMessage;

public class SocketProtocolHandler extends IoHandlerAdapter {
	
	private SocketBusinessMessageHandler socketBusinessMessageHandler; 

	public SocketBusinessMessageHandler getSocketBusinessMessageHandler() {
		return socketBusinessMessageHandler;
	}

	public void setSocketBusinessMessageHandler(SocketBusinessMessageHandler socketBusinessMessageHandler) {
		this.socketBusinessMessageHandler = socketBusinessMessageHandler;
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
		try{
			SocketRequestMessage socketRequestMessage = (SocketRequestMessage)message;
			SocketResponseMessage socketResponseMessage = socketBusinessMessageHandler.messageReceived(socketRequestMessage , session) ;
			if(null!=socketResponseMessage){ 
				session.write(socketResponseMessage).addListener(IoFutureListener.CLOSE);
			}
		}catch(Exception e){
			e.printStackTrace() ;
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
