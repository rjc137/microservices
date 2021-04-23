package com.sample.emessenger.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sample.emessenger.api.entity.MessageEntity;
import com.sample.emessenger.api.entity.Topic;

@Repository
public interface MessageEntityRespository extends JpaRepository<MessageEntity, Long> {

	List<MessageEntity> findByTopic(Topic topic);

	List<MessageEntity> findById(String id);

}
