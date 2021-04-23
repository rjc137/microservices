package com.sample.emessenger.api.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sample.emessenger.api.entity.User;
import com.sample.emessenger.api.repository.UserRepository;

@Service
public class EMessengerUserDetailsServiceImpl implements UserDetailsService {

	UserRepository userRepository;

	@Autowired
	public EMessengerUserDetailsServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(username)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
		return EMessengerUserDetailsImpl.build(user);
	}

}
