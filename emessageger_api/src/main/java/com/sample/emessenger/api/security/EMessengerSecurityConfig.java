package com.sample.emessenger.api.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.sample.emessenger.api.entity.util.ERole;
import com.sample.emessenger.api.security.jwt.JwtUtil;
import com.sample.emessenger.api.service.EMessengerUserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class EMessengerSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private EMessengerUserDetailsServiceImpl userDetailsService;

	@Autowired
	private AuthenticationEntryPoint authEntryPoint;

	private JwtUtil jwtUtil;

	@Override
	public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.userDetailsService(userDetailsService)
				.passwordEncoder(eMessengerBCryptPasswordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable()
		.exceptionHandling().authenticationEntryPoint(authEntryPoint).and()
		.authorizeRequests().antMatchers("/signUp/**").permitAll()
		.antMatchers("/signIn/**","/api-docs/**","/swagger-ui/**","/swagger-ui.html", "/actuator/health/**").permitAll()
		.antMatchers("/test/**").hasAuthority(ERole.ROLE_ADMIN.name())
		.anyRequest().authenticated().and()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

	http.addFilterBefore(authenticationJwtTokenFilter(jwtUtil, userDetailsService), UsernamePasswordAuthenticationFilter.class);
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public AuthTokenFilter authenticationJwtTokenFilter(JwtUtil jwtUtils, UserDetailsService userDetailsService) {
		return new AuthTokenFilter(jwtUtils, userDetailsService);
	}

	@Bean
	public PasswordEncoder eMessengerBCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
