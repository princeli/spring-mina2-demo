package com.princeli.message;

public class BinaryMessage {
	
	private int length;
	
	private String messageCode;

	private byte[] data;

	public BinaryMessage(int length , String messageCode , byte[] data){
		this.length = length ;
		this.messageCode = messageCode ;
		this.data = data ;
	}
	
	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public String getMessageCode() {
		return messageCode;
	}

	public void setMessageCode(String messageCode) {
		this.messageCode = messageCode;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}
}
