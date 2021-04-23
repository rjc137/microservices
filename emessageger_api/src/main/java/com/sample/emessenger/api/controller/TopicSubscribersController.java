package com.sample.emessenger.api.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sample.emessenger.api.service.EMessengerService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/topics/{name}/subscribers")
@AllArgsConstructor
public class TopicSubscribersController {

	private EMessengerService service;
	
	/***
	 * Lists usernames of users subcribed to a specified topic.
	 * 
	 * @param topic Topic of the messages
	 * @return Names of users subscribed to the topic
	 */
	@GetMapping
	public ResponseEntity<List<String>> listSubscribers(@PathParam("name") String topic) {
		log.info("Listing Subscribers by Topic");
		return ResponseEntity.ok(service.listSubscribers(topic));
	}

	/***
	 * Subscribes the user to the topic
	 * 
	 * @param username Name of the user
	 * @param topic    Topic of the messages
	 * @return True is the user was properly subscribed, False otherwise
	 */
	@PatchMapping()
	public ResponseEntity<Boolean> subscribe(@RequestParam(name = "username", required = true) String username,
			@PathParam("name") String topic) {
		log.info("Subscribing User to Topic");
		return ResponseEntity.ok(service.subscribe(username, topic));
	}

	/***
	 * Unsubscribes the user from the topic
	 * 
	 * @param username Name of the user
	 * @param topic    Topic of the messages
	 * @return True is the user was properly unsubscribed, False otherwise
	 */
	@PatchMapping("/messages/unsubscribe")
	public ResponseEntity<Boolean> unsubscribe(@RequestParam(name = "username", required = true) String username,
			@PathParam("name") String topic) {
		log.info("UnSubscribing User from Topic");
		return ResponseEntity.ok(service.unsubscribe(username, topic));
	}
}
