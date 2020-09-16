package com.glusers.api.exceptions;

public class Response {
	
	Object object;
	
	public Response() {
		super();
	}

	public Response(Object o) {
		super();
		this.object = o;
	}

	public Object getMessage() {
		return object;
	}

	public void setMessage(Object obj) {
		this.object = obj;
	}
	
}
