package com.communitygaming.interview.controller;

import javax.validation.Valid;

import java.util.List;
import java.util.stream.Collectors;

import com.communitygaming.interview.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.communitygaming.interview.payload.request.LoginRequest;
import com.communitygaming.interview.payload.request.SignupRequest;
import com.communitygaming.interview.payload.response.JwtResponse;
import com.communitygaming.interview.payload.response.MessageResponse;
import com.communitygaming.interview.security.jwt.JwtUtils;
import com.communitygaming.interview.security.service.UserDetailsImpl;

@CrossOrigin(origins = "localhost", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/signin")
    public ResponseEntity<JwtResponse> authenticateUser(@Valid @RequestBody final LoginRequest loginRequest) {
        final JwtResponse jwtResponse = userService.authenticateUser(loginRequest);

        return ResponseEntity.ok(jwtResponse);
    }

    @PostMapping("/signup")
    public ResponseEntity<MessageResponse> registerUser(@Valid @RequestBody final SignupRequest signUpRequest) {
        final MessageResponse serviceResponse = userService.registerUser(signUpRequest);

        return ResponseEntity.ok(serviceResponse);
    }
}
