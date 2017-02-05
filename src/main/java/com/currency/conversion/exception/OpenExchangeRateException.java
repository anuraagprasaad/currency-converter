package com.currency.conversion.exception;

public class OpenExchangeRateException extends Exception {

	private static final long serialVersionUID = 1L;

	public OpenExchangeRateException() {
		super();
	}

	public OpenExchangeRateException(String message, Throwable cause) {
		super(message, cause);
	}

	public OpenExchangeRateException(String message) {
		super(message);
	}

	public OpenExchangeRateException(Throwable cause) {
		super(cause);
	}
}