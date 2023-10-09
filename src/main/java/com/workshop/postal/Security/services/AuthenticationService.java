package com.workshop.postal.Security.services;

import com.workshop.postal.Security.dtos.SignUpRequestDto;
import com.workshop.postal.Security.dtos.SigninRequestDto;
import com.workshop.postal.Security.dtos.JwtAuthenticationResponseDto;

public interface AuthenticationService {
    JwtAuthenticationResponseDto signup(SignUpRequestDto request);
    JwtAuthenticationResponseDto signin(SigninRequestDto request);
}
