package com.udacity.jwdnd.c1.review.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.udacity.jwdnd.c1.review.entity.ChatForm;
import com.udacity.jwdnd.c1.review.service.MessageService;

@Controller
@RequestMapping("/chat")
public class ChatController {

	private MessageService messageService;
	
	// Constructor injection for messageService
	public ChatController(MessageService messageService) {
		this.messageService = messageService;
	}
	
	@GetMapping
	public String showChat( Model theModel) {
		
		// create ChatForm object
		ChatForm chatForm = new ChatForm();

		// Add to the model attribute
		theModel.addAttribute("chatForm", chatForm);
		
		return "chat";
	}
	
	@PostMapping
	public String processMessage(Authentication authentication, ChatForm chatForm, Model theModel) {
		
		chatForm.setUsername(authentication.getName());
		messageService.addMessage(chatForm);
		chatForm.setMessageText("");
		
		theModel.addAttribute("messages", messageService.getMessages());
		
		return "chat";
	}
	
	@ModelAttribute("allMessageTypes")
	public String[] getAllMessageTypes() {
		return new String[] {"Say", "Shout", "Whisper"};
	}
}
