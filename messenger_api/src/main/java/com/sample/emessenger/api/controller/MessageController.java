package com.sample.emessenger.api.controller;

import java.util.Date;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sample.emessenger.api.dto.CreateMessage;
import com.sample.emessenger.api.entity.Message;
import com.sample.emessenger.api.service.EMessengerServiceImpl;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/messages")
@AllArgsConstructor
public class MessageController {

	private final Logger log = LoggerFactory.getLogger(MessageController.class);

	private final EMessengerServiceImpl service;

	/***
	 * Retrieves a message with specified identifier
	 * 
	 * @param id Message identifier
	 * @return Message
	 */
	@GetMapping("/messages/{id}")
	public ResponseEntity<Message> retrieveMessage(@PathVariable(name = "id", required = false) String id) {
		log.info("Listing Message By Id");
		return ResponseEntity.ok(service.retrieveMessage(id));
	}
	
	/***
	 * Stores the message on the server
	 * 
	 * @param message Message to be stored
	 */
	@PostMapping
	public void postMessage(@RequestBody CreateMessage message) {
		log.info("Posting Message");
		service.postMessage(message);
	}

	

	/***
	 * Lists identifiers and timestamps of all messages stored on the server on
	 * specified topic
	 * 
	 * @param topic Topic of the messages
	 * @return Map of messages' identifiers and timestamps, null if there are no
	 */
	@GetMapping("/messages/timestamps")
	public ResponseEntity<Map<String, Date>> listMessagesWithTimestamps(
			@RequestParam(name = "topic", required = false) String topic) {
		log.info("Listing Messages with Timestamps");
		return ResponseEntity.ok(service.listMessagesWithTimestamps(topic));
	}

}