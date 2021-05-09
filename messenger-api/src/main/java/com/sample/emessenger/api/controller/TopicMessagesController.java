package com.sample.emessenger.api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sample.emessenger.api.entity.Message;
import com.sample.emessenger.api.service.EMessengerService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/topics/{name}/messages")
@AllArgsConstructor
public class TopicMessagesController {

	private final EMessengerService service;

	/***
	 * Lists identifiers of all messages stored on the server on specified topic
	 * 
	 * @param topic Topic of the messages
	 * @return List of messages' identifiers
	 */
	@GetMapping
	public ResponseEntity<List<Message>> listMessages(@PathVariable(name = "name", required = false) String topic) {
		log.info("Listing Messages By Topic");
		return ResponseEntity.ok(service.listMessages(topic));
	}
}
