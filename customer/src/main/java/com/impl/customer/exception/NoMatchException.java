package com.impl.customer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason="Not Found")
public class NoMatchException extends OnlineException {

	public NoMatchException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

}
