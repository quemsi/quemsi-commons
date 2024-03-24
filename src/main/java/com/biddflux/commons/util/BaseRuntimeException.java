package com.biddflux.commons.util;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class BaseRuntimeException extends RuntimeException {
    private static final long serialVersionUID = -2787248463270168252L;
    private final HttpStatus status;
    private final String messageId;
	private String message;
	private Map<String, Object> extra;

    public BaseRuntimeException(HttpStatus status, String messageId, Map<String, Object> extra, Throwable cause) {
		super(cause);
		this.status = status;
		this.messageId = messageId;
		this.extra = extra;
	}
    
    public BaseRuntimeException(HttpStatus httpStatus, String messageId, Throwable t) {
        super(messageId, t);
        this.status = httpStatus;
        this.messageId = messageId;
    }

    public BaseRuntimeException(HttpStatus httpStatus, String messageId) {
    	super(messageId);
    	this.status = httpStatus;
        this.messageId = messageId;
    }

    public BaseRuntimeException(HttpStatus httpStatus, String messageId, String message) {
    	super(message);
    	this.status = httpStatus;
        this.messageId = messageId;
        this.message = message;
    }
    
    public Map<String, Object> getExtra(){
    	return extra;
    }
    
    public BaseRuntimeException withExtra(String key, Object val) {
    	if(this.extra == null) {
    		this.extra = new HashMap<>();
    	}
    	this.extra.put(key, val);
    	return this;
    }
    
    public BaseRuntimeException(HttpStatus httpStatus) {
    	this(httpStatus, null);
    }
    
    @Override
	public String getMessage() {
		StringBuilder s = new StringBuilder();
		s.append("messageId : ").append(messageId).append(" extra : ").append(getExtra());
		if(message != null) {
			s.append("message :").append(message);
		}
		if(getCause() != null) {
			s.append("caused by").append(getCause().getMessage());
		}
		return s.toString();
	}
	
	@Override
	public void printStackTrace(PrintWriter s) {
		s.append("messageId : " + messageId + " extra : " + getExtra());
		s.append("\n");
		super.printStackTrace(s);
	}
}

