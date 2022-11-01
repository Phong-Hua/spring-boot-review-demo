package com.udacity.jwdnd.c1.review.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import com.udacity.jwdnd.c1.review.entity.ChatMessage;

@Mapper
public interface MessageMapper {

	@Select("SELECT * FROM MESSAGES")
	List<ChatMessage> getMessages();
	
	@Insert("INSERT INTO MESSAGES(username, messagetext) VALUES(#{username}, #{messageText})")
	@Options(useGeneratedKeys = true, keyProperty = "messageId")
	int addMessage(ChatMessage message);
}
