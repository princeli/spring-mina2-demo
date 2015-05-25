package com.princeli.codec;

import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;
import org.apache.mina.filter.codec.demux.MessageEncoder;

import com.princeli.message.SocketResponseMessage;

/**
 * Socket 报文加密类
 * @author miller
 *
 */
public class SocketResponseEncoder implements MessageEncoder<SocketResponseMessage> {

public static String defaultEncoding;
	
	private CharsetDecoder decoder ;
	
	public SocketResponseEncoder(){
		decoder = Charset.forName(defaultEncoding).newDecoder();
	}
	
	
	@Override
	public void encode(IoSession session, SocketResponseMessage message,
			ProtocolEncoderOutput out) throws Exception {
		try{
			IoBuffer buf = IoBuffer.allocate(256);
			buf.setAutoExpand(true);
			byte[] conArray = new byte[12+message.getContent().length] ;
			byte[] lengthArray = String.format("%04d", conArray.length-4).getBytes();  
			byte[] tradeArray = message.getTradeCode().getBytes() ;
			byte[] returnArray = message.getReturnCode() ;
			
			System.arraycopy(lengthArray, 0, conArray, 0, 4) ; //交易报文长度 
			System.arraycopy(tradeArray, 0, conArray, 4, 4) ; //交易类型
			System.arraycopy(returnArray, 0, conArray, 8, 4) ; //交易返回状态
			
			System.arraycopy(message.getContent(), 0, conArray, 12, message.getContent().length) ;    
			String s = new String(conArray) ;  
			buf.put(conArray) ;
			buf.flip();
			out.write(buf);
		}catch(Exception e){
			e.printStackTrace() ;
		}
	}


	public static String getDefaultEncoding() {
		return defaultEncoding;
	}


	public static void setDefaultEncoding(String defaultEncoding) {
		SocketResponseEncoder.defaultEncoding = defaultEncoding;
	}


	public CharsetDecoder getDecoder() {
		return decoder;
	}


	public void setDecoder(CharsetDecoder decoder) {
		this.decoder = decoder;
	}
}
