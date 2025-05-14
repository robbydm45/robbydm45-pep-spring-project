package com.example.service;

import java.util.List;
import java.util.Optional;

import org.h2.security.auth.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.entity.*;
import com.example.exception.BlankMessageException;
import com.example.exception.InvalidAccountException;
import com.example.exception.InvalidMessageIdException;
import com.example.exception.MessageLengthException;
import com.example.repository.*;

@Service
public class MessageService {

    private MessageRepository messageRepository;
    private AccountService accountService;

    /*
     * MessageService constructor. 
     * 
     * @param messageRepository a message repository object.
     */
    @Autowired
    public MessageService(MessageRepository messageRepository, AccountService accountService) {
        this.messageRepository = messageRepository;
        this.accountService = accountService;
    }

    /*
     * Method to retrieve all messages.
     * 
     * @return list of Message objects.
     */
    public List<Message> getAllMessages() {
        return (List<Message>) messageRepository.findAll();
    }

    /*
     * Method to retrieve message by give message id.
     * 
     * @param messageId the message id to search for.
     * @return the specified message or null if message does not exist.
     */
    public Message getMessageById(Integer messageId) {
        Optional<Message> optionalMessage = messageRepository.findById(messageId);
        if (optionalMessage.isPresent()) {
            return optionalMessage.get();
        } else {
            return null;
        }
        
    }

    /*
     * Mehthod to save message to database.
     * 
     * @param message the message to save.
     * @return the saved message including the generated message id.
     */
    @Modifying
    public Message addMessage(Message message) throws BlankMessageException, MessageLengthException, InvalidAccountException {
        if (message.getMessageText().isBlank()) {
            throw new BlankMessageException();
        } else if(message.getMessageText().length() > 255) {
            throw new MessageLengthException();
        } else if(!accountService.validAccount(message.getPostedBy())) {
            throw new InvalidAccountException();
        } else {
            Message addedMessage = messageRepository.save(message);
            return addedMessage;
        }
    }

    /*
     * Method to update a message given the message id.
     * 
     * @param messageId the message id in which to update.
     * @param message the updated message.
     * @return 1 if the message saved properly or otherwise null.
     */
    @Modifying
    public Integer updateMessage(Integer messageId, Message message) throws BlankMessageException, MessageLengthException, InvalidMessageIdException{
        if (message.getMessageText().isBlank()) {
            throw new BlankMessageException();
        } else if (message.getMessageText().length() > 255) {
            throw new MessageLengthException();
        } else {
            Optional<Message> optionalMessage = messageRepository.findMessageByMessageId(messageId);
            if (optionalMessage.isPresent()) {
                Message updatedMessage = optionalMessage.get();
                updatedMessage.setMessageText(message.getMessageText());
                messageRepository.save(updatedMessage);
                return 1;
            } else {
                throw new InvalidMessageIdException();
            }
        }
    }

    /*
     * Method to delete a message by given message id;
     * 
     * @param messageId the message's id in which to delete.
     * @return the number of rows deleted or null otherwise.
     */
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
