package com.sample.emessenger.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sample.emessenger.api.entity.Topic;

public interface TopicRepository extends JpaRepository<Topic, Long> {

	Optional<Topic> findByName(String name);

	@Query("select t.name from Topic t")
	List<String> getTopicNames();

}
