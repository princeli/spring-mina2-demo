package com.princeli.codec;


import org.apache.mina.filter.codec.demux.DemuxingProtocolCodecFactory;

import com.princeli.message.SocketResponseMessage;

public class SocketServerProtocolCodecFactory extends DemuxingProtocolCodecFactory {

	 public SocketServerProtocolCodecFactory(String charset){
		 SocketRequestDecoder.defaultEncoding = charset ;
		 SocketResponseEncoder.defaultEncoding = charset ;
		 super.addMessageDecoder(SocketRequestDecoder.class) ;
		 super.addMessageEncoder(SocketResponseMessage.class, SocketResponseEncoder.class) ; 
	 }
}
