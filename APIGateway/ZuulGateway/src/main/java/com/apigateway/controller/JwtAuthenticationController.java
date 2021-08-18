package com.apigateway.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apigateway.entity.JwtRequest;
import com.apigateway.entity.JwtResponse;
import com.apigateway.entity.UserEntity;
import com.apigateway.security.JwtUtil;

@RestController
@CrossOrigin()
@RequestMapping("/token")
public class JwtAuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private JwtUtil jwtUtil;

	@PostMapping("/generate-token")
	public ResponseEntity<Object> generateToken(@RequestBody JwtRequest jwtRequest) {
		authenticate(jwtRequest.getUsername(), jwtRequest.getPassword());
		UserDetails userDetails = userDetailsService.loadUserByUsername(jwtRequest.getUsername());
		String token = jwtUtil.generateToken(userDetails);
		return ResponseEntity.ok(new JwtResponse(token));
	}

	public void authenticate(String username, String password) {

		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new DisabledException("USER_DISABLED ");
		} catch (BadCredentialsException e) {
			throw new BadCredentialsException("INVALID_CREDENTIALS ");
		}
	}

	@GetMapping("/current-user")
	public UserEntity getCurrentUser(Principal principal) {
		return (UserEntity) userDetailsService.loadUserByUsername(principal.getName());
	}
}
