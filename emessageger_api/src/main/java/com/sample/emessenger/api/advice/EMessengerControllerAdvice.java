package com.sample.emessenger.api.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.sample.emessenger.api.controller.MessageController;
import com.sample.emessenger.api.dto.ErrorMessage;
import com.sample.emessenger.api.exception.ErrorCodes;
import com.sample.emessenger.api.exception.MessageNotFoundException;
import com.sample.emessenger.api.exception.TopicNotFoundException;
import com.sample.emessenger.api.exception.UserAlreadySubscribedException;
import com.sample.emessenger.api.exception.UserNotFoundException;
import com.sample.emessenger.api.exception.UserNotSubscribedException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice(assignableTypes = { MessageController.class })
public class EMessengerControllerAdvice extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = { MessageNotFoundException.class })
	public ResponseEntity<Object> handleMessageNotFoundException(MessageNotFoundException ex) {
		log.error("message not found exception: ", ex.getMessage());
		if (ErrorCodes.MESSAGE_NOT_FOUND == ex.getErrorCodes()) {
			return new ResponseEntity<>(
					new ErrorMessage(ex.getErrorCodes().getId(), ex.getErrorCodes().getDescription()),
					HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(new ErrorMessage(ErrorCodes.INTERNAL_SERVER_ERROR.getId(),
				ErrorCodes.INTERNAL_SERVER_ERROR.getDescription()), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(value = { TopicNotFoundException.class })
	public ResponseEntity<Object> handleTopicNotFoundException(TopicNotFoundException ex) {
		log.error("topic not found exception", ex.getMessage());
		if (ErrorCodes.TOPIC_NOT_FOUND == ex.getErrorCodes()) {
			return new ResponseEntity<>(
					new ErrorMessage(ex.getErrorCodes().getId(), ex.getErrorCodes().getDescription()),
					HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(new ErrorMessage(ErrorCodes.INTERNAL_SERVER_ERROR.getId(),
				ErrorCodes.INTERNAL_SERVER_ERROR.getDescription()), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(value = { UserAlreadySubscribedException.class })
	public ResponseEntity<Object> handleUserAlreadySubscribedException(UserAlreadySubscribedException ex) {
		log.error("user already subscribed exception", ex.getMessage());
		if (ErrorCodes.USER_ALREADY_SUBSCRIBED == ex.getErrorCodes()) {
			return new ResponseEntity<>(
					new ErrorMessage(ex.getErrorCodes().getId(), ex.getErrorCodes().getDescription()),
					HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(new ErrorMessage(ErrorCodes.INTERNAL_SERVER_ERROR.getId(),
				ErrorCodes.INTERNAL_SERVER_ERROR.getDescription()), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(value = { UserNotFoundException.class })
	public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException ex) {
		log.error("user not found exception ", ex.getMessage());
		if (ErrorCodes.USER_NOT_FOUND == ex.getErrorCodes()) {
			return new ResponseEntity<>(
					new ErrorMessage(ex.getErrorCodes().getId(), ex.getErrorCodes().getDescription()),
					HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(new ErrorMessage(ErrorCodes.INTERNAL_SERVER_ERROR.getId(),
				ErrorCodes.INTERNAL_SERVER_ERROR.getDescription()), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(value = { UserNotSubscribedException.class })
	public ResponseEntity<Object> handleUserNotSubscribedException(UserNotSubscribedException ex) {
		log.error("user not subscribed exception", ex.getMessage());
		if (ErrorCodes.USER_NOT_SUBSCRIBED_TO_TOPIC == ex.getErrorCodes()) {
			return new ResponseEntity<>(
					new ErrorMessage(ex.getErrorCodes().getId(), ex.getErrorCodes().getDescription()),
					HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(new ErrorMessage(ErrorCodes.INTERNAL_SERVER_ERROR.getId(),
				ErrorCodes.INTERNAL_SERVER_ERROR.getDescription()), HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
