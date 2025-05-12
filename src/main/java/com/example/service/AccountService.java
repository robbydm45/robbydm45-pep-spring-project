package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.*;
import com.example.repository.AccountRepository;
import com.example.repository.MessageRepository;

@Service
public class AccountService {

    private AccountRepository accountRepository;
    private MessageRepository messageRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository, MessageRepository messageRepository) {
        this.accountRepository = accountRepository;
        this.messageRepository = messageRepository;
    }

    public List<Message> getMessagesByAccountId(Integer accountId) {
        return messageRepository.findAllMessagesByPostedBy(accountId);
    }

    public Account registerUser(Account account) {
        Account newAccount = accountRepository.save(account);
        return newAccount;
    }

    public Account accountLogin(Account account) {
        Optional<Account> validAccount = accountRepository.findAccountByUsernameAndPassword(account.getUsername(), account.getPassword());
        if (validAccount.isPresent()) {
            return validAccount.get();
        } else {
            return null;
        }
    }
}
