package com.sample.emessenger.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sample.emessenger.api.dto.JwtLoginResponse;
import com.sample.emessenger.api.dto.LoginRequest;
import com.sample.emessenger.api.dto.MessageResponse;
import com.sample.emessenger.api.dto.UserSignUpRequest;
import com.sample.emessenger.api.service.LoginService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

@RestController
@CrossOrigin(maxAge = 3600)
public class LoginController {

	@Autowired
	LoginService loginService;

	public LoginController(LoginService loginService) {
		this.loginService = loginService;
	}

	@Operation(summary = "Get a book by its id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Found the book", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = LoginRequest.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
			@ApiResponse(responseCode = "404", description = "Book not found", content = @Content) })
	@PostMapping("/signIn")
	public ResponseEntity<JwtLoginResponse> authenticateUser(@RequestBody LoginRequest loginRequest) {
		return ResponseEntity.ok(loginService.logInUser(loginRequest));
	}

	@PostMapping("/signUp")
	public ResponseEntity<MessageResponse> registerUser(@RequestBody UserSignUpRequest request) {
		if (loginService.createUser(request)) {
			return ResponseEntity.ok(new MessageResponse("User Created Successfully"));
		} else {
			return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).build();
		}
	}

	@GetMapping("/test")
	public ResponseEntity<MessageResponse> test() {
		return ResponseEntity.ok(new MessageResponse("Login Request Successful!"));

	}

}
