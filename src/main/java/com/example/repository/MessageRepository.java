package com.example.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.entity.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {

    List<Message> findAll();

    Optional<Message> findByMessageId(Integer messageId);

    @Query("DELETE FROM Message m WHERE m.messageId = :messageId")
    Integer deleteByMessageId(@Param("messageId") Integer messageId);
}
