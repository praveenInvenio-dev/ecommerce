package com.pavi.ecom.Exception;

public class NotFoundException extends Exception {

	private String msg;

	public NotFoundException(String msg) {
		super(msg);
	}
}
