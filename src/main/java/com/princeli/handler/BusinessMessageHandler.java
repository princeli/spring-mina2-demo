package com.princeli.handler;

import com.princeli.exception.BusinessMessageException;
import com.princeli.exception.MimaMessageException;
import com.princeli.message.HttpRequestMessage;
import com.princeli.message.HttpResponseMessage;

/**
 * 业务处理Handler
 * 根据相应的服务类型，查找对应的服务方法
 * @author miller
 *
 */
@SuppressWarnings("unused")
public interface BusinessMessageHandler {

	//HTTP 请求测试
	public final static String HTTPTEST = "httpTestService" ;
	
	//接口类型例子
	public final static String INTERFACEEXAMPLE = "interfaceExample" ;
	
	public HttpResponseMessage requestServer(HttpRequestMessage request) throws BusinessMessageException ;
	
	public HttpResponseMessage interfaceExample(HttpRequestMessage request) throws BusinessMessageException ;
	
	//Mina执行主方法
	public HttpResponseMessage messageReceived(HttpRequestMessage request) throws MimaMessageException ; 
	
}
