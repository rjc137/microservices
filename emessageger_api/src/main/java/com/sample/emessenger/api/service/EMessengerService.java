package com.sample.emessenger.api.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.sample.emessenger.api.dto.Message;

public interface EMessengerService {

	void postMessage(Message message);

	List<Message> listMessages(String topic);

	Map<String, Date> listMessagesWithTimestamps(String topic);

	Message retrieveMessage(String id);

	List<String> listTopics();

	boolean subscribe(String username, String topic);

	boolean unsubscribe(String username, String topic);

	List<String> listSubscribers(String topic);

	List<String> listNodes();
}
