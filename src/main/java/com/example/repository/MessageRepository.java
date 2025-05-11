package com.example.repository;

import java.lang.StackWalker.Option;
import java.util.List;
import java.util.Optional;

import org.apache.tomcat.jni.Time;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.entity.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {

    List<Message> findAll();

    Optional<Message> findByMessageId(Integer messageId);

    Message save(Message message);

    List<Message> findAllMessagesByPostedBy(Integer postedBy);

    Integer deleteByMessageId(Integer messageId);
}
