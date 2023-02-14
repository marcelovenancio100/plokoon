package br.com.vnx.context;

import java.util.HashMap;
import java.util.Map;

public class ContextResponseCustom {
	private int statusCode;
	private Map<String, String> headers;
	private Object body;
	
	public ContextResponseCustom(int statusCode, Map<String, String> headers, Object body) {
		this.statusCode = statusCode;
		this.headers = headers;
		this.body = body;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public Map<String, String> getHeaders() {
		return headers;
	}
	
	public Object getBody() {
		return body;
	}
	
	public static Builder builder() {
		return new Builder();
	}
	
	public static class Builder {
		private int statusCode = 200;
		private Map<String, String> headers = new HashMap<String, String>();
		private Object body;
		
		public Builder setStatusCode(int statusCode) {
			this.statusCode = statusCode;
			return this;
		}
		
		public Builder setHeaders(Map<String, String> headers) {
			this.headers = headers;
			return this;
		}
		
		public Builder setBody(Object body) {
			this.body = body;
			return this;
		}
		
		public ContextResponseCustom build() {
			headers.put("X-Powered-By", "AWS Lambda & Serverless");
			return new ContextResponseCustom(statusCode, headers, body);
		}
	}
}