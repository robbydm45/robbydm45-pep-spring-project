package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.entity.*;
import com.example.repository.*;

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
            return optionalMessage.get();
        } else {
            return null;
        }
    }

    @Modifying
    public Message addMessage(Message message) {
        Message addedMessage = messageRepository.save(message);
        return addedMessage;
    }

    @Modifying
    public Integer updateMessage(Integer messageId, Message message) {
        Optional<Message> optionalMessage = messageRepository.findMessageByMessageId(messageId);
        if (optionalMessage.isPresent()) {
            Message updatedMessage = optionalMessage.get();
            updatedMessage.setMessageText(message.getMessageText());
            messageRepository.save(updatedMessage);
            return 1;
        } else {
            return null;
        }
    }

    @Transactional
    public Integer deleteMessageById(Integer messageId) {
        Optional<Message> optionalMessage = messageRepository.findMessageByMessageId(messageId);
        if (optionalMessage.isPresent()) {
            Integer rowsDeleted = messageRepository.deleteByMessageId(messageId);
            return rowsDeleted;
        } else {
            return null;
        }
    }
}
