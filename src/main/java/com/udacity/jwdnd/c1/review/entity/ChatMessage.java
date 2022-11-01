package com.udacity.jwdnd.c1.review.entity;

public class ChatMessage {

	private int messageId;
	private String username;
	private String messageText;
	
	public ChatMessage() {
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getMessageId() {
		return messageId;
	}

	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}

	public String getMessageText() {
		return messageText;
	}

	public void setMessageText(String messageText) {
		this.messageText = messageText;
	}

	@Override
	public String toString() {
		return "ChatMessage [messageId=" + messageId + ", username=" + username + ", messageText=" + messageText + "]";
	}
}
