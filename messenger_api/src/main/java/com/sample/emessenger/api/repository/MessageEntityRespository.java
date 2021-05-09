package com.sample.emessenger.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sample.emessenger.api.entity.Message;
import com.sample.emessenger.api.entity.Topic;

@Repository
public interface MessageEntityRespository extends JpaRepository<Message, Long> {

	List<Message> findByTopic(Topic topic);

	List<Message> findById(String id);

}
