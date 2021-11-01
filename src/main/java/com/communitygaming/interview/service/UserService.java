package com.communitygaming.interview.service;

import com.communitygaming.interview.payload.request.LoginRequest;
import com.communitygaming.interview.payload.request.SignupRequest;
import com.communitygaming.interview.payload.response.JwtResponse;
import com.communitygaming.interview.payload.response.MessageResponse;

public interface UserService {

    MessageResponse registerUser(SignupRequest signUpRequest);
    JwtResponse authenticateUser(LoginRequest loginRequest);
}
