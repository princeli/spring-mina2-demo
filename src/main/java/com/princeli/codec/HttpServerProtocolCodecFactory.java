package com.princeli.codec;

import org.apache.mina.filter.codec.demux.DemuxingProtocolCodecFactory;

import com.princeli.message.HttpResponseMessage;

public class HttpServerProtocolCodecFactory extends DemuxingProtocolCodecFactory {
	public HttpServerProtocolCodecFactory(String encoding) {
		HttpRequestDecoder.defaultEncoding = encoding ;
		HttpResponseEncoder.defaultEncoding = encoding ;
		super.addMessageDecoder(HttpRequestDecoder.class);
		super.addMessageEncoder(HttpResponseMessage.class, HttpResponseEncoder.class);
	}

}
