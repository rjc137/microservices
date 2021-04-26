package com.sample.authorization.api.dto;

import java.io.Serializable;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserSignUpRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6780322413879533836L;

	@JsonProperty(value = "email", required = true)
	private String email;

	@JsonProperty(value = "password",required = true)
	private String password;

	@JsonProperty("mobile")
	private String mobile;

	@JsonProperty("address")
	private String address;

	@JsonProperty("roles")
	private Set<String> roles;
}
