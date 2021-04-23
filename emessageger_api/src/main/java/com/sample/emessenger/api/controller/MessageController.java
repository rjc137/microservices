package com.sample.emessenger.api.controller;

import java.util.Collection;
import java.util.Date;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sample.emessenger.api.dto.Message;
import com.sample.emessenger.api.service.EMessengerServiceImpl;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/messages")
@AllArgsConstructor
public class MessageController {

	private final Logger log = LoggerFactory.getLogger(MessageController.class);

	private final EMessengerServiceImpl service;

	@GetMapping
	public ResponseEntity<Collection<Message>> getAllMessages(){
		log.info("Retrieving all messages");
		return ResponseEntity.ok(service.getAllMessages());
	}
	
	
	/***
	 * Retrieves a message with specified identifier
	 * 
	 * @param id MessageEntity identifier
	 * @return MessageEntity
	 */
	@GetMapping("/messages/{id}")
	public ResponseEntity<Message> retrieveMessage(@RequestParam(name = "id", required = false) String id) {
		log.info("Listing MessageEntity By Id");
		return ResponseEntity.ok(service.retrieveMessage(id));
	}
	
	/***
	 * Stores the message on the server
	 * 
	 * @param message MessageEntity to be stored
	 */
	@PostMapping
	public void postMessage(@RequestBody Message message) {
		log.info("Posting MessageEntity");
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