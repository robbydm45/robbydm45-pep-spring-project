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

    /*
     * Constructor method.
     * 
     * @param accountRepository an account repository object.
     * @param messageRepository a message repository object.
     */
    @Autowired
    public AccountService(AccountRepository accountRepository, MessageRepository messageRepository) {
        this.accountRepository = accountRepository;
        this.messageRepository = messageRepository;
    }

    /*
     * Method to retieve all messages for a specified account.
     * 
     * @param accountId the account id to search for.
     * @return list of messages.
     */
    public List<Message> getMessagesByAccountId(Integer accountId) {
        return messageRepository.findAllMessagesByPostedBy(accountId);
    }

    /*
     * Method to register a new user and save to database.
     * 
     * @param account an account object.
     * @return the saved account.
     */
    public Account registerUser(Account account) {
        Account newAccount = accountRepository.save(account);
        return newAccount;
    }

    /*
     * Method to check if account exists in the database by just using the account id.
     * 
     * @param accountId the account id to search for.
     * @return true if the account exists or false otherwise.
     */
    public boolean validAccount(Integer accountId) {
        Optional<Account> isValid = accountRepository.findAccountByAccountId(accountId);
        if (isValid.isPresent()) {
            return true;
        } else {
            return false;
        }
    }

    /*
     * Method for logging in a user using an account object.
     * 
     * @param account an account object.
     * @return the valid account or null if not found.
     */
    public Account accountLogin(Account account) {
        Optional<Account> validAccount = accountRepository.findAccountByUsernameAndPassword(account.getUsername(), account.getPassword());
        if (validAccount.isPresent()) {
            return validAccount.get();
        } else {
            return null;
        }
    }
}
