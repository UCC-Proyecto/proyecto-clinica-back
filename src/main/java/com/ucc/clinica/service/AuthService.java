package com.ucc.clinica.service;

import com.ucc.clinica.dto.request.LoginRequest;
import com.ucc.clinica.dto.response.LoginResponse;

public interface AuthService {
    LoginResponse login(LoginRequest loginRequest);
}
