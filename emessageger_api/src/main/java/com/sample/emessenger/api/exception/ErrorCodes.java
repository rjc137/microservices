package com.sample.emessenger.api.exception;

import lombok.Getter;

@Getter
public enum ErrorCodes {

	MESSAGE_NOT_FOUND(300, "Message not Found"), TOPIC_NOT_FOUND(301, "Topic not found"),
	USER_NOT_FOUND(302, "User not found"), USER_NOT_SUBSCRIBED_TO_TOPIC(303, "User not subscribed to topic"),
	USER_ALREADY_SUBSCRIBED(304, "User already subscribed to topic"),
	INTERNAL_SERVER_ERROR(500, "Internal server error has occured"), INVALID_LOGIN(305, "Invalid login"),
	USER_EXISTS(306, "User Already Exists"), ROLE_NOT_FOUND(307, "Role Not Found"),
	USER_CREATION_ERROR(308, "Error Creating User");

	private int id;
	private String description;

	private ErrorCodes(int id, String description) {
		this.id = id;
		this.description = description;
	}
}
