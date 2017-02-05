package com.currency.conversion.exception;

public class UserExitstException extends Exception {

	private static final long serialVersionUID = 1L;

	public UserExitstException() {
		super();
	}

	public UserExitstException(String message, Throwable cause) {
		super(message, cause);
	}

	public UserExitstException(String message) {
		super(message);
	}

	public UserExitstException(Throwable cause) {
		super(cause);
	}
}