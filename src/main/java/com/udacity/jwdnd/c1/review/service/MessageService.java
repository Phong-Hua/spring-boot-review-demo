package com.udacity.jwdnd.c1.review.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.udacity.jwdnd.c1.review.entity.ChatForm;
import com.udacity.jwdnd.c1.review.entity.ChatMessage;

@Service
public class MessageService {
	
	private List<ChatMessage> messages;
	
	public MessageService() {
	}
	
	public void addMessage(ChatForm chatForm) {
		
		/**
		 * We can do the logic here anywhere,
		 * but often the service layer contains business logic,
		 * so it is better to do it here.
		 */
		
		ChatMessage message = new ChatMessage();
		message.setUsername(chatForm.getUsername());
		
		switch (chatForm.getMessageType()) {
			case "Shout":
				message.setMessageText(chatForm.getMessageText().toUpperCase());
				break;
			case "Whisper":
				message.setMessageText(chatForm.getMessageText().toLowerCase());
				break;
			default:
				message.setMessageText(chatForm.getMessageText());
				break;
		}
		messages.add(message);
	}
	
	public List<ChatMessage> getMessages() {
		return messages;
	}
	
	@PostConstruct
	public void postConstruct() {
		messages = new ArrayList<>();
		System.out.println("Creating MessageService bean");
	}
}
