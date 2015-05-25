package com.princeli.handler;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.apache.mina.core.session.IoSession;

import com.princeli.common.BinaryEndpoint;
import com.princeli.exception.MimaMessageException;
import com.princeli.message.BinaryMessage;
import com.princeli.message.SocketResponseMessage;

public abstract class SocketAbstractMessageHandler implements SocketMessageHandler { 
 

	protected Map<String, Method> messageMethodMap = new HashMap<String, Method>();

	protected SocketAbstractMessageHandler() {
		initMessageHandler();
	} 
	
	private void initMessageHandler() {
		Class serviceClass = this.getClass();
		Method[] methods = serviceClass.getMethods();
		for (int i = 0; i < methods.length; i++) {
			if(methods[i].isAnnotationPresent(MessageInvocation.class)) {
				MessageInvocation messageHandler = methods[i].getAnnotation(MessageInvocation.class);
				String messageCode = messageHandler.value();
				this.messageMethodMap.put(messageCode, methods[i]);
			}
		}
	}

	
	@Override
	public SocketResponseMessage process(BinaryMessage message, BinaryEndpoint binaryEndpoint, IoSession session) throws MimaMessageException {
		String messageCode = message.getMessageCode() ;
		
		if(null==messageCode || "".equals(messageCode)){
			throw new MimaMessageException("process messageCode is null , messageCode=" + messageCode);
		}
		
		Method handler = this.messageMethodMap.get(messageCode);
		
		if (handler == null) {
			throw new MimaMessageException("no matched handler, messageCode=" + messageCode);
		}
		
		try {
			return (SocketResponseMessage)handler.invoke(this, message, binaryEndpoint, session);
		} catch (Exception e) {
			e.printStackTrace();
			throw new MimaMessageException("handler execute error, messageCode=" + messageCode);
		}
	}

}
