package com.example.web;

public enum StatusCode {
	Continue(100, "continue"),
	Processing(102,"processing"),
	Success(200,"ok"),
	Created(201,"created"),
	Accepted(202,"accepted"),
	
	BadRequest(400,"bad Request"),
	UnAuthorized(401, "unauthorized"),
	Forbidden(403,"forbidden"),
	NotFound(404, "not Found"),
	MethodNotAllowed(405,"method not allowed"),
	NotAcceptable(406, "not acceptable"),
	
	InternalServerError(500,"internal server error");
/*	public final static StatusCode Success = new StatusCode(200,"OK");
	public final static StatusCode InternalServerError = new StatusCode(500,"internal serrver error");*/
	
	int code;
	String  reason;
	
	private StatusCode(int code, String reason) {
		this.code = code;
		this.reason = reason;
	}
	
	@Override
	public String toString(){
		return "{ code:"+code+", reason:"+reason+"}";
	}
	
}
