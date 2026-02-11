package com.chetraseng.taskflow_api.services;

import com.chetraseng.taskflow_api.dto.requests.LoginRequest;
import com.chetraseng.taskflow_api.dto.requests.UserRequest;
import com.chetraseng.taskflow_api.dto.responses.AuthResponse;
import com.chetraseng.taskflow_api.dto.responses.UserResponse;

public interface AuthService {


    UserResponse signUp(UserRequest  userRequest);
     AuthResponse login(LoginRequest loginRequest);
}
