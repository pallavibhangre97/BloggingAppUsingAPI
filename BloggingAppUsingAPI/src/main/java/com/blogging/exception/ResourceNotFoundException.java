package com.blogging.exception;

public class ResourceNotFoundException extends RuntimeException {

	private int id;

	public ResourceNotFoundException(int id) {
		super(id + " not found");
		this.id = id;
	}

}
