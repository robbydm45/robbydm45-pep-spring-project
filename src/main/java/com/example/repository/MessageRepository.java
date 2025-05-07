package com.example.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.entity.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {

    List<Message> findAll();

    Optional<Message> findByMessageId(Integer messageId);
    List<Message> findAllMessagesByAccountId(Integer accountId);

    @Transactional
    Integer deleteByMessageId(Integer messageId);
}
