package com.princeli.handler;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import com.princeli.exception.MimaMessageException;
import com.princeli.message.HttpRequestMessage;
import com.princeli.message.HttpResponseMessage;




public abstract class AbstractMessageHandler implements MessageHandler { 

	protected Map<String, Method> messageMethodMap = new HashMap<String, Method>();

	protected AbstractMessageHandler() {
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
	

	public HttpResponseMessage process(HttpRequestMessage request) throws MimaMessageException {
		String messageCode = request.getParameter("type") ;
		if(null==messageCode || "".equals(messageCode)){
			throw new MimaMessageException("process messageCode is null , messageCode=" + messageCode);
		}
		Method handler = this.messageMethodMap.get(messageCode);
		if (handler == null) {
			throw new MimaMessageException("no matched handler, messageCode=" + messageCode);
		}
		
		try {
			return (HttpResponseMessage) handler.invoke(this, request);
		} catch (Exception e) {
			e.printStackTrace();
			throw new MimaMessageException("handler execute error, messageCode=" + messageCode);
		}
	}


}
