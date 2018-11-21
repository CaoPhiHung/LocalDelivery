package main.java.model;

public class Message {

	public int statusCode; // -1: fail/error create -- 0: fail to update/read/write
	public int objectID;
	public String message;
	
	public Message(int statusCode, int objectId, String errorMessage) {
		this.statusCode = statusCode;
		this.objectID = objectId;
		this.message = errorMessage;
	}
}
