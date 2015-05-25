package com.princeli.handler;


import org.apache.mina.core.session.IoSession;

import com.princeli.common.BinaryEndpoint;
import com.princeli.exception.MimaMessageException;
import com.princeli.message.BinaryMessage;
import com.princeli.message.SocketResponseMessage;


public interface SocketMessageHandler {

	SocketResponseMessage process(BinaryMessage message, BinaryEndpoint binaryEndpoint, IoSession session) throws MimaMessageException ;
}