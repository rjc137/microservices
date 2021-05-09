package com.sample.emessenger.api.exception;

import lombok.Getter;

public class UserNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6527864018406515246L;
	@Getter
	private final ErrorCodes errorCodes;

	public UserNotFoundException(Exception e, ErrorCodes errorCodes) {
		super(errorCodes.getDescription(), e);
		this.errorCodes = errorCodes;
	}

	public UserNotFoundException(ErrorCodes errorCodes) {
		super(errorCodes.getDescription());
		this.errorCodes = errorCodes;
	}
}