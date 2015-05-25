package com.princeli.message;

import com.princeli.util.ByteUtil;
 
public class SocketRequestMessage {

	/**
	 * 交易码
	 */
	private String tradeCode ;
	
	/**
	 * 交易报文
	 */
	private byte[] content ;
	
	/**
	 * 报文长度
	 */
	private int length ;
	
	

	public SocketRequestMessage(){} ;
	
	public SocketRequestMessage(byte[] arrays){
		try{
			byte[] lengthBytes = ByteUtil.subArray(arrays, 0, 4);
			length = Integer.parseInt(new String(lengthBytes)) ;
			
			byte[] codeBytes = null;
			byte[] dataBytes = null;
			
			//消息码部分
			if (arrays.length >= 8) {
				codeBytes = ByteUtil.subArray(arrays, 4, 8);
			} else {
				codeBytes = new byte[0];
			}
			tradeCode = new String(codeBytes) ; 
			
			
			//包体部分 
			if (arrays.length > 8) {
				dataBytes = ByteUtil.subArray(arrays, 8, arrays.length); 
			} else {
				dataBytes = new byte[0];
			}
			content = dataBytes ;
		}catch(Exception e){
			e.printStackTrace() ;
		}
	}
	
	
	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	

	public String getTradeCode() {
		return tradeCode;
	}

	public void setTradeCode(String tradeCode) {
		this.tradeCode = tradeCode;
	}

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}
}
