package com.hxchassi.ms.business.exception;

public class GenericEntityException extends RuntimeException {

	private static final long serialVersionUID = -7195250948921639737L;

	public GenericEntityException() {
		super();
	}

	public GenericEntityException(String message) {
		super(message);
	}
}