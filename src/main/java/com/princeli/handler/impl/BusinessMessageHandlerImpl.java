package com.princeli.handler.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.princeli.exception.BusinessMessageException;
import com.princeli.exception.MimaMessageException;
import com.princeli.handler.AbstractMessageHandler;
import com.princeli.handler.BusinessMessageHandler;
import com.princeli.handler.MessageInvocation;
import com.princeli.message.HttpRequestMessage;
import com.princeli.message.HttpResponseMessage;
import com.princeli.service.ExampleService;

@SuppressWarnings("unused")
public class BusinessMessageHandlerImpl extends AbstractMessageHandler implements BusinessMessageHandler {
	
	private static final Logger logger = LoggerFactory.getLogger(BusinessMessageHandlerImpl.class);
	
 
	@Autowired
	private ExampleService exampleService ;
	
	@Override
	@MessageInvocation(HTTPTEST)
	public HttpResponseMessage requestServer(HttpRequestMessage request) throws BusinessMessageException {
		String type = request.getParameter("type");
		HttpResponseMessage response = new HttpResponseMessage();
		response.setContentType("text/plain; charset=utf-8");
		response.setResponseCode(HttpResponseMessage.HTTP_STATUS_SUCCESS);
		response.appendBody("***** spring-mina HTTP服务正常运行 " + type);
		return response;
	}

	@Override
	@MessageInvocation(INTERFACEEXAMPLE)
	public HttpResponseMessage interfaceExample(HttpRequestMessage request)
			throws BusinessMessageException {
		String context = request.getContext();
		logger.info(context);
		return exampleService.interfaceExample(request);
	}

	@Override
	public HttpResponseMessage messageReceived(HttpRequestMessage request) throws MimaMessageException {
		return process(request);
	}
}
