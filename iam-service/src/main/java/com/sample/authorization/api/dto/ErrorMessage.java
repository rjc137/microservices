package com.sample.authorization.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorMessage {
	@JsonProperty("error_code")
	private int errorCode;
	@JsonProperty("error_message")
	private String message;
}
