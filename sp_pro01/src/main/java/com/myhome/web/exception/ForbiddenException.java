package com.myhome.web.exception;

public class ForbiddenException extends Exception{
	
	public void UnauthorizedException() {}
	
	public void  UnauthorizedException(String message) {
		super(message, new Throwable());
	}

}
