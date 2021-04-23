package com.sample.emessenger.api.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JwtLoginResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2355297075135429455L;

	@JsonProperty("token")
	private String token;

	@JsonProperty("email")
	private String email;

	@JsonProperty("roles")
	private List<String> roles;
}
