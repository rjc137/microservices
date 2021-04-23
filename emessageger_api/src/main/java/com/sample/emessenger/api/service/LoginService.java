package com.sample.emessenger.api.service;

import com.sample.emessenger.api.dto.JwtLoginResponse;
import com.sample.emessenger.api.dto.LoginRequest;
import com.sample.emessenger.api.dto.UserSignUpRequest;

public interface LoginService {
	public boolean createUser(UserSignUpRequest loginRequest);
	public JwtLoginResponse logInUser(LoginRequest loginRequest);
}
