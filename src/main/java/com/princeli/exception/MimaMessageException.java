package com.princeli.exception;


/**
 * Mina 交互异常
 * @author miller
 *
 */
public class MimaMessageException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1711039906897575144L;
	
	private String messageCode;

	public MimaMessageException() {
		this("Unknown error");
	}

	public MimaMessageException(String message) {
		this(message, "-1");
	}

	public MimaMessageException(String message, String messageCode) {
		super(message);
		this.messageCode = messageCode;
	}

	public MimaMessageException(String message, String messageCode, Throwable t) {
		super(message, t);
		this.messageCode = messageCode;
	}

	public String getMessageCode() {
		return messageCode;
	}

}
