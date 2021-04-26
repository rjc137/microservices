package com.sample.authorization.api.service;

import org.springframework.stereotype.Service;

import com.sample.authorization.api.dto.JwtLoginResponse;
import com.sample.authorization.api.dto.LoginRequest;
import com.sample.authorization.api.dto.UserSignUpRequest;
@Service
public interface LoginService {
	public boolean createUser(UserSignUpRequest loginRequest);
	public JwtLoginResponse logInUser(LoginRequest loginRequest);
}
