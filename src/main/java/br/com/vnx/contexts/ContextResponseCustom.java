package br.com.vnx.contexts;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Collections;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import software.amazon.awssdk.utils.Logger;

public class ContextResponseCustom {
	private int statusCode;
	private Map<String, String> headers;
	private String body;
	private boolean isBase64Encoded;
	
	public ContextResponseCustom(int statusCode, Map<String, String> headers, String body, boolean isBase64Encoded) {
		this.statusCode = statusCode;
		this.headers = headers;
		this.body = body;
		this.isBase64Encoded = isBase64Encoded;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public String getBody() {
		return body;
	}

	public Map<String, String> getHeaders() {
		return headers;
	}

	public boolean isBase64Encoded() {
		return isBase64Encoded;
	}
	
	public static Builder builder() {
		return new Builder();
	}
	
	public static class Builder {
		private static final Logger LOG = Logger.loggerFor(ContextResponseCustom.Builder.class);
		private static final ObjectMapper objectMapper = new ObjectMapper();
		
		private int statusCode = 200;
		private Map<String, String> headers = Collections.emptyMap();
		private String rawBody;
		private Object objBody;
		private byte[] binaryBody;
		private boolean base64Encoded;
		
		public Builder setStatusCode(int statusCode) {
			this.statusCode = statusCode;
			return this;
		}
		
		public Builder setHeaders(Map<String, String> headers) {
			this.headers = headers;
			return this;
		}
		
		public Builder setRawBody(String rawBody) {
			this.rawBody = rawBody;
			return this;
		}
		
		public Builder setObjBody(Object objBody) {
			this.objBody = objBody;
			return this;
		}
		
		public Builder setBinaryBody(byte[] binaryBody) {
			this.binaryBody = binaryBody;
			setBase64Encoded(true);
			return this;
		}
		
		public Builder setBase64Encoded(boolean base64Encoded) {
			this.base64Encoded = base64Encoded;
			return this;
		}
		
		public ContextResponseCustom build() {
			String body = null;
			
			if (rawBody != null) {
				body = rawBody;
			} else if (objBody != null) {
				try {
					body = objectMapper.writeValueAsString(objBody);
				} catch (JsonProcessingException e) {
					LOG.error(() -> "Erro ao serializar objeto.", e);
					throw new RuntimeException(e);
				}
			} else if (binaryBody != null) {
				body = new String(Base64.getEncoder().encode(binaryBody), StandardCharsets.UTF_8);
			}
			
			return new ContextResponseCustom(statusCode, headers, body, base64Encoded);
		}
	}
}
