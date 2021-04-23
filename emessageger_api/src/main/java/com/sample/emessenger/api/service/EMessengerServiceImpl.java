package com.sample.emessenger.api.service;

import static com.sample.emessenger.api.exception.ErrorCodes.MESSAGE_NOT_FOUND;
import static com.sample.emessenger.api.exception.ErrorCodes.TOPIC_NOT_FOUND;
import static com.sample.emessenger.api.exception.ErrorCodes.USER_NOT_SUBSCRIBED_TO_TOPIC;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sample.emessenger.api.dto.Message;
import com.sample.emessenger.api.entity.MessageEntity;
import com.sample.emessenger.api.entity.Topic;
import com.sample.emessenger.api.entity.User;
import com.sample.emessenger.api.exception.ErrorCodes;
import com.sample.emessenger.api.exception.MessageNotFoundException;
import com.sample.emessenger.api.exception.TopicNotFoundException;
import com.sample.emessenger.api.exception.UserNotFoundException;
import com.sample.emessenger.api.exception.UserNotSubscribedException;
import com.sample.emessenger.api.repository.MessageEntityRespository;
import com.sample.emessenger.api.repository.TopicRepository;
import com.sample.emessenger.api.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional(value = TxType.SUPPORTS)
@Slf4j
public class EMessengerServiceImpl implements EMessengerService {

	private MessageEntityRespository messageEntityRespository;
	private UserRepository userRepository;
	private TopicRepository topicRepository;

	@Autowired
	public EMessengerServiceImpl(MessageEntityRespository messageEntityRespository, UserRepository userRepository,
			TopicRepository topicRepository) {
		this.messageEntityRespository = messageEntityRespository;
		this.userRepository = userRepository;
		this.topicRepository = topicRepository;
	}

	@Override
	@Transactional(value = TxType.REQUIRES_NEW)
	public void postMessage(Message message) {
		log.debug(String.format("Posting message with id : %s ", message.getId()));
		MessageEntity entity = new MessageEntity(null, message.getId(), message.getTimeStamp(), message.getSender(),
				message.getContent(), null, null, null);
		Topic topic = topicRepository.findByName(message.getTopic())
				.orElse(new Topic(null, message.getTopic(), Collections.emptyList(), Collections.emptyList(), null));
		entity.setTopic(topic);
		messageEntityRespository.save(entity);
	}

	@Override
	public List<Message> listMessages(String topicName) {
		log.debug(String.format("Listing message by topic : %s ", topicName));
		Topic topic = topicRepository.findByName(topicName)
				.orElseThrow(() -> new TopicNotFoundException(TOPIC_NOT_FOUND));
		List<MessageEntity> entities = messageEntityRespository.findByTopic(topic);
		return entities.stream().map(EMessengerServiceImpl::convertMessageEntityToMessage).collect(Collectors.toList());
	}

	@Override
	public Map<String, Date> listMessagesWithTimestamps(String topicName) {
		log.debug(String.format("Listing message ids with timestamps for topic : %s ", topicName));
		Topic topic = topicRepository.findByName(topicName)
				.orElseThrow(() -> new TopicNotFoundException(TOPIC_NOT_FOUND));

		List<MessageEntity> results = messageEntityRespository.findByTopic(topic);

		Map<String, Date> mapOfIdsDates = new HashMap<>();
		for (MessageEntity messageEntity : results) {
			mapOfIdsDates.put(messageEntity.getId(), new Date(messageEntity.getTimeStamp()));
		}
		return mapOfIdsDates;
	}

	@Override
	public Message retrieveMessage(String id) {
		log.debug(String.format("Retrieving message by id : %s ", id));
		MessageEntity row = messageEntityRespository.findById(id).stream().findFirst()
				.orElseThrow(() -> new MessageNotFoundException(MESSAGE_NOT_FOUND));
		return new Message(row.getId(), row.getTimeStamp(), row.getSender(), row.getTopic().getName(),
				row.getContent());
	}

	@Override
	public List<String> listTopics() {
		log.debug("Listing all topics");
		List<Topic> topics = topicRepository.findAll();
		return topics.stream().map(EMessengerServiceImpl::getNameFromTopic).collect(Collectors.toList());
	}

	@Override
	@Transactional(value = TxType.REQUIRES_NEW)
	public boolean subscribe(String username, String topicName) {
		Topic topic = topicRepository.findByName(topicName)
				.orElseThrow(() -> new TopicNotFoundException(TOPIC_NOT_FOUND));
		User user = userRepository.findByName(username)
				.orElseThrow(() -> new UserNotFoundException(ErrorCodes.USER_NOT_FOUND));

		List<User> subscribers = Optional.ofNullable(topic.getSubscribers()).orElseGet(Collections::emptyList);
		subscribers.add(user);
		topicRepository.save(topic);

		return true;
	}

	@Override
	@Transactional(value = TxType.REQUIRES_NEW)
	public boolean unsubscribe(String username, String topicName) {

		Topic topic = topicRepository.findByName(topicName)
				.orElseThrow(() -> new TopicNotFoundException(TOPIC_NOT_FOUND));
		User user = userRepository.findByName(username)
				.orElseThrow(() -> new UserNotFoundException(ErrorCodes.USER_NOT_FOUND));

		List<User> subscribers = Optional.ofNullable(topic.getSubscribers()).orElseGet(Collections::emptyList);

		if (subscribers.contains(user)) {
			subscribers.remove(user);
			return true;
		} else {
			throw new UserNotSubscribedException(USER_NOT_SUBSCRIBED_TO_TOPIC);
		}
	}

	@Override
	public List<String> listSubscribers(String topicName) {
		log.debug(String.format("Listing Subscribers for topic : %s ", topicName));
		Topic topicResult = topicRepository.findByName(topicName)
				.orElseThrow(() -> new TopicNotFoundException(TOPIC_NOT_FOUND));
		return topicResult.getSubscribers().stream().map(EMessengerServiceImpl::getNameFromUser)
				.collect(Collectors.toList());
	}

	@Override
	public List<String> listNodes() {
		return new ArrayList<>();
	}

	private static String getNameFromUser(User user) {
		return user.getName();
	}

	private static String getNameFromTopic(Topic topic) {
		return topic.getName();
	}

	private static Message convertMessageEntityToMessage(MessageEntity messageEntity) {
		return new Message(messageEntity.getId(), messageEntity.getTimeStamp(), messageEntity.getSender(),
				messageEntity.getTopic().getName(), messageEntity.getContent());
	}

	public Collection<Message> getAllMessages() {
		return messageEntityRespository.findAll().stream().map(EMessengerServiceImpl::convertMessageEntityToMessage)
				.collect(Collectors.toList());
	}

}
