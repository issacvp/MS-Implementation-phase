package com.impl.customer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason="Bad Request")
public class OnlineException extends RuntimeException {

	public OnlineException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public OnlineException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	
}
