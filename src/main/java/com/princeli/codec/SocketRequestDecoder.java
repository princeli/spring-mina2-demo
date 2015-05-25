package com.princeli.codec;

import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;
import org.apache.mina.filter.codec.demux.MessageDecoderAdapter;
import org.apache.mina.filter.codec.demux.MessageDecoderResult;

 


import com.princeli.message.SocketRequestMessage;
import com.princeli.util.ByteUtil;

public class SocketRequestDecoder extends MessageDecoderAdapter {
	
	public static String defaultEncoding;
	
	private CharsetDecoder decoder ;
	
	public SocketRequestDecoder(){
		decoder = Charset.forName(defaultEncoding).newDecoder();
	}

	@Override
	public MessageDecoderResult decodable(IoSession session, IoBuffer in) {
		// 判断是否收全报文,前四个字节表示报文长度
		byte[] buf = new byte[in.remaining()]; 
		in.get(buf);
		byte[] lengthBytes = ByteUtil.subArray(buf, 0, 4);
		int totalLength = Integer.parseInt(new String(lengthBytes));
		if (totalLength > (buf.length - 4)) {
			return MessageDecoderResult.NEED_DATA;
		} else {
			return MessageDecoderResult.OK;
		}
	}

	@Override
	public MessageDecoderResult decode(IoSession session, IoBuffer in,
			ProtocolDecoderOutput out) throws Exception {
		byte[] buf = new byte[in.remaining()];  
		in.get(buf);
		
		SocketRequestMessage socketRequestMessage = new SocketRequestMessage(buf) ;
		if(null==socketRequestMessage){
			return MessageDecoderResult.NEED_DATA ;  
		}
		
		out.write(socketRequestMessage) ; 
		return MessageDecoderResult.OK;  
	}

	public static String getDefaultEncoding() {
		return defaultEncoding;
	}

	public static void setDefaultEncoding(String defaultEncoding) {
		SocketRequestDecoder.defaultEncoding = defaultEncoding;
	}

	public CharsetDecoder getDecoder() {
		return decoder;
	}

	public void setDecoder(CharsetDecoder decoder) {
		this.decoder = decoder;
	}
	
}
