package com.example.cactusrestclient;

public class Response {
	
	private String identifier;
	private String representation;
	private String status;
	private String response;
	
	public Response(String identifier, String representation, String status, String response) {
		this.setIdentifier(identifier);
		this.setRepresentation(representation);
		this.setStatus(status);
		this.setResponse(response);
	}

	public String getRepresentation() {
		return representation;
	}

	public void setRepresentation(String representation) {
		this.representation = representation;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}
	
	@Override
	public String toString() {  
		return "identifier: " + this.getIdentifier() + ", representation: " + 
			this.getRepresentation() + 
			", status: "+ this.getStatus() +
			", response: "+ this.getResponse();  
	}  

}
