package br.com.caique.exception;

import java.io.Serializable;
import java.util.Date;

public class ExceptionResponse implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Date timestamp;
	private String message;
	private String status;

	public ExceptionResponse(Date timestamp, String message, String status) {
		super();
		this.setTimestamp(timestamp);
		this.setMessage(message);
		this.setStatus(status);
	}
	
	public Date getTimestamp() {
		return timestamp;
	}
	
	private void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getMessage() {
		return message;
	}

	private void setMessage(String message) {
		this.message = message;
	}
	
	public String getStatus() {
		return status;
	}

	private void setStatus(String status) {
		this.status = status;
	}
	
}
