package com.sample.emessenger.api.exception;

import lombok.Getter;

public class MessageNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6527864018406515246L;
	@Getter
	private final ErrorCodes errorCodes;

	public MessageNotFoundException(Exception e, ErrorCodes errorCodes) {
		super(errorCodes.getDescription(), e);
		this.errorCodes = errorCodes;
	}

	public MessageNotFoundException(ErrorCodes errorCodes) {
		super(errorCodes.getDescription());
		this.errorCodes = errorCodes;
	}
}