package com.kaishengit.exception;

public class SQLAccessException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public SQLAccessException() {
	}

	public SQLAccessException(String message, Throwable cause) {
		super(message, cause);
	}

	public SQLAccessException(String message) {
		super(message);
	}

	public SQLAccessException(Throwable cause) {
		super(cause);
	}

}
