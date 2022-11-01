package com.udacity.jwdnd.c1.review.service;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.udacity.jwdnd.c1.review.dao.MessageMapper;
import com.udacity.jwdnd.c1.review.entity.ChatForm;
import com.udacity.jwdnd.c1.review.entity.ChatMessage;

@Service
public class MessageService {
	
	private MessageMapper messageMapper;
	
	public MessageService(MessageMapper messageMapper) {
		this.messageMapper = messageMapper;
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
		
		messageMapper.addMessage(message);
	}
	
	public List<ChatMessage> getMessages() {
		return messageMapper.getMessages();
	}
	
	@PostConstruct
	public void postConstruct() {

		System.out.println("Creating MessageService bean");
	}
}
