package com.example.service;

import com.example.repository.MessageRepository;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.*;

@Service
public class MessageService {

    private MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public List<Message> getAllMessages() {
        return (List<Message>) messageRepository.findAll();
    }

    public Message getMessageById(int messageId) {
        return (Message) messageRepository.findByMessageId(messageId);
    }
}
