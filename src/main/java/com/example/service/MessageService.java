package com.example.service;

import com.example.repository.MessageRepository;
import java.util.List;
import java.util.Optional;

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

    public Message getMessageById(Integer messageId) {
        Optional<Message> optionalMessage = messageRepository.findById(messageId);
        if (optionalMessage.isPresent()) {
            Message message = optionalMessage.get();
            return message;
        } else {
            return null;
        }
    }

    public Integer deleteMessageById(Integer messageId) {
        Optional<Message> optionalMessage = messageRepository.findByMessageId(messageId);
        if (optionalMessage.isPresent()) {
            Integer rowsDeleted = messageRepository.deleteByMessageId(messageId);
            return rowsDeleted;
        } else {
            return null;
        }
    }
}
