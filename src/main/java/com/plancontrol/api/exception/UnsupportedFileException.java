package com.plancontrol.api.exception;

public class UnsupportedFileException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UnsupportedFileException() {
		super("Unsupported file extension");
	}

	public UnsupportedFileException(String message) {
		super(message);
	}
}
