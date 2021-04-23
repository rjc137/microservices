package com.sample.authorization.api.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sample.authorization.api.dto.JwtLoginResponse;
import com.sample.authorization.api.dto.LoginRequest;
import com.sample.authorization.api.dto.UserSignUpRequest;
import com.sample.authorization.api.entity.Role;
import com.sample.authorization.api.entity.Roles;
import com.sample.authorization.api.entity.User;
import com.sample.authorization.api.exception.ErrorCodes;
import com.sample.authorization.api.exception.UserLoginException;

import com.sample.authorization.api.repository.UserRepository;
import com.sample.authorization.api.security.jwt.JwtUtil;
import com.sample.authorization.api.repository.RoleRepository;

@Service
public class LoginServiceImpl implements LoginService {

    private UserRepository userRepository;

    private RoleRepository roleRepository;

    private PasswordEncoder encoder;

    private AuthenticationManager authenticationManager;

    private JwtUtil jwtUtil;

    public LoginServiceImpl(UserRepository userRepository, PasswordEncoder encoder, AuthenticationManager authenticationManager, RoleRepository roleRepository, JwtUtil jwtUtil) {
        super();
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.authenticationManager = authenticationManager;
        this.roleRepository = roleRepository;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public boolean createUser(UserSignUpRequest loginRequest) {
        if (userRepository.existsByEmail(loginRequest.getEmail()).booleanValue()) {
            throw new UserLoginException(ErrorCodes.USER_EXISTS);
        }

        User user = new User(null, loginRequest.getEmail(), encoder.encode(loginRequest.getPassword()), null, null); 

        Set<String> strRoles = loginRequest.getRoles();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null || strRoles.isEmpty()) {
            Role userRole = roleRepository.findByName(Roles.ROLE_USER).orElseThrow(() -> new UserLoginException(ErrorCodes.ROLE_NOT_FOUND));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                case "admin":
                    Role adminRole = roleRepository.findByName(Roles.ROLE_ADMIN).orElseThrow(() -> new UserLoginException(ErrorCodes.ROLE_NOT_FOUND));
                    roles.add(adminRole);

                    break;
                case "superuser":
                    Role modRole = roleRepository.findByName(Roles.ROLE_SUPERUSER).orElseThrow(() -> new UserLoginException(ErrorCodes.ROLE_NOT_FOUND));
                    roles.add(modRole);

                    break;
                default:
                    Role userRole = roleRepository.findByName(Roles.ROLE_USER).orElseThrow(() -> new UserLoginException(ErrorCodes.ROLE_NOT_FOUND));
                    roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        try {
            userRepository.save(user);
        } catch (Exception e) {
            throw new UserLoginException(e, ErrorCodes.USER_CREATION_ERROR);
        }

        return true;
    }

    @Override
    public JwtLoginResponse logInUser(LoginRequest loginRequest) {
        Authentication authentication = null;
        try {
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        } catch (AuthenticationException e) {
            throw new UserLoginException(e, ErrorCodes.INVALID_LOGIN);
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtil.generateToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());

        return new JwtLoginResponse(jwt, userDetails.getEmail(), roles);

    }

}
