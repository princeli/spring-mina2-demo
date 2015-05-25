package com.princeli.service;

import java.net.URLEncoder;
 
import java.text.SimpleDateFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.princeli.common.ErrorsCode;
import com.princeli.exception.BusinessMessageException;
import com.princeli.message.HttpRequestMessage;
import com.princeli.message.HttpResponseMessage;

@Service
public class ExampleService {
	private static final Logger logger = LoggerFactory.getLogger(ExampleService.class);
	
	public HttpResponseMessage interfaceExample(HttpRequestMessage request)
			throws BusinessMessageException {
		logger.info("进入interfaceExample方法");
		HttpResponseMessage httpResponseMessage = null;
		httpResponseMessage = new HttpResponseMessage();
		httpResponseMessage = new HttpResponseMessage();
		httpResponseMessage.setContentType("text/html; charset=utf-8");
		httpResponseMessage.setResponseCode(200);
		try{
			httpResponseMessage.appendBody("hello world");
		}catch(Exception e){
			e.printStackTrace() ;
			return response(ErrorsCode.EXCEPTION,"example服务异常") ;  
		}
		logger.info("离开interfaceExample方法");
		return httpResponseMessage ;
	}
	
	
	public HttpResponseMessage response(String...strings)
			throws BusinessMessageException {
		HttpResponseMessage httpResponseMessage = null;
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
			httpResponseMessage = new HttpResponseMessage();
			httpResponseMessage.setContentType("text/html");
			httpResponseMessage.setResponseCode(200);

			if ((strings != null) && (strings.length > 0)) {
				httpResponseMessage.appendBody("returnCode=" + strings[0]+ "&message=" + URLEncoder.encode(strings[1], "UTF-8"));
				System.out.println("失败原因：" + strings[1]);
				return httpResponseMessage;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessMessageException(e.getMessage());
		}
		return httpResponseMessage;
	}
}
