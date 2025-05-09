package com.example.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Message;
import com.example.service.AccountService;
import com.example.service.MessageService;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
@RestController
public class SocialMediaController {

    private AccountService accountService;
    private MessageService messageService;

    @Autowired
    public SocialMediaController(AccountService accountService, MessageService messageService) {
        this.accountService = accountService;
        this.messageService = messageService;
    }

   @GetMapping("/messages")
    public ResponseEntity<List<Message>> getAllMessages() {
        return ResponseEntity.status(200).body(messageService.getAllMessages());
    }

    @GetMapping("/messages/{messageId}")
    public ResponseEntity<Message> getMessageById(@PathVariable Integer messageId) {
        return ResponseEntity.status(200).body(messageService.getMessageById(messageId));
    }

    @PatchMapping("/messages/{messageId}")
    public ResponseEntity<String> patchMessage(@PathVariable Integer messageId) {
        return null; //ResponseEntity.status(200).body();
    }

    @DeleteMapping("/messages/{messageId}")
    public ResponseEntity<Integer> deleteMessageById(@PathVariable Integer messageId) {
        return ResponseEntity.status(200).body(messageService.deleteMessageById(messageId));
    }


    @GetMapping("/accounts/{accountId}/messages")
    public ResponseEntity<List<Message>> getMessagesByAccountId(@PathVariable("accountId") Integer accountId) {
        List<Message> messageList = accountService.getMessagesByAccountId(accountId);
        return ResponseEntity.status(200).body(messageList);
    }
}
