package com.princeli.handler;


import com.princeli.exception.MimaMessageException;
import com.princeli.message.HttpRequestMessage;
import com.princeli.message.HttpResponseMessage;


public interface MessageHandler {

	HttpResponseMessage process(HttpRequestMessage request) throws MimaMessageException ;
}