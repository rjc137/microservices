package com.sample.emessenger.api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sample.emessenger.api.service.EMessengerService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/topics")
@AllArgsConstructor
public class TopicController {
	
	
	private final EMessengerService service;
	
	
	/***
	 * Lists all topics present in the stored messages
	 * 
	 * @return List of unique topics
	 */
	@GetMapping("/topics")
	public ResponseEntity<List<String>> listTopics() {
		log.info("Listing Topics");
		return ResponseEntity.ok(service.listTopics());
	}

}
