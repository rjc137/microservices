package com.sample.emessenger.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateMessage {

	@JsonProperty(value = "timeStamp", required = true)
	private long timeStamp;
	@JsonProperty(value = "sender", required = true)
	private String sender;
	@JsonProperty(value = "topic")
	private String topic;
	@JsonProperty(value = "content", required = true)
	private String content;
}
