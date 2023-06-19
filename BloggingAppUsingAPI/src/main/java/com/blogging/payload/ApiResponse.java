package com.blogging.payload;

public class ApiResponse {
	private String Message;
	private Boolean success;

	public ApiResponse(String message, Boolean success) {
		super();
		Message = message;
		this.success = success;
	}

	public String getMessage() {
		return Message;
	}

	public void setMessage(String message) {
		Message = message;
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

}
