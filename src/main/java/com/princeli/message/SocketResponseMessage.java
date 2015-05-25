package com.princeli.message;

import com.princeli.common.ErrorsCode;

public class SocketResponseMessage {

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
	
	/**
	 * 返回状态
	 */
	private byte[] returnCode = ErrorsCode.EXCEPTION.getBytes()  ;

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

	public int getLength() {
		if(this.length <= 0){
			int len = this.content.length + 8 ;
			return len ;
		}
		return 0 ;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public byte[] getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(byte[] returnCode) {
		this.returnCode = returnCode;
	}
}
