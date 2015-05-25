package com.princeli.common;

import java.nio.ByteBuffer;

import org.apache.mina.core.session.IoSession;

public class BinaryEndpoint {

	private IoSession ioSession;
	
	
	public BinaryEndpoint(IoSession ioSession){
		this.ioSession = ioSession ;
	}
	
	public void response(byte[] data) {
		ByteBuffer wb = ByteBuffer.allocate(data.length);
		wb.put(data);
		wb.flip();
		ioSession.write(wb);
	}
}
