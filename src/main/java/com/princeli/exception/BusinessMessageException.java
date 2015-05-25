package com.princeli.exception;

/**
 * 业务交易异常
 * @author miller
 *
 */
public class BusinessMessageException extends RuntimeException{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String messageCode;

	public BusinessMessageException() {
		this("Unknown error");
	}

	public BusinessMessageException(String message) {
		this(message, "-1");
	}

	public BusinessMessageException(String message, String messageCode) {
		super(message);
		this.messageCode = messageCode;
	}

	public BusinessMessageException(String message, String messageCode, Throwable t) {
		super(message, t);
		this.messageCode = messageCode;
	}

	public String getMessageCode() {
		return messageCode;
	}
}
