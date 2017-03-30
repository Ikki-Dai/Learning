package com.example.web;

import java.util.Map;

public class JsonResponse {
	private StatusCode statusCode;
	private Map<String, Object> data;
	
	
	public JsonResponse() {
		super();
	}

	public JsonResponse(StatusCode statusCode, Map<String, Object> data) {
		super();
		this.statusCode = statusCode;
		this.data = data;
	}

	public StatusCode getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(StatusCode statusCode) {
		this.statusCode = statusCode;
	}

	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}

    @Override
    public String toString() {
        return "JsonResponse : {statusCode : " + statusCode + ", data : " + data + "}";
    }
	
}
