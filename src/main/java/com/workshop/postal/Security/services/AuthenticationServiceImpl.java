package com.workshop.postal.Security.services;

import com.workshop.postal.Security.Jwt.JwtService;
import com.workshop.postal.Security.dtos.SignUpRequestDto;
import com.workshop.postal.Security.dtos.SigninRequestDto;
import com.workshop.postal.Security.dtos.JwtAuthenticationResponseDto;
import com.workshop.postal.Security.User.SecurityUser;
import com.workshop.postal.Security.User.Role;
import com.workshop.postal.Security.User.SecurityUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final SecurityUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public JwtAuthenticationResponseDto signup(SignUpRequestDto request) {
        var user = SecurityUser.builder().firstName(request.getFirstName()).lastName(request.getLastName())
                .email(request.getEmail()).password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER).build();

        userRepository.save(user);

        var jwt = jwtService.generateToken(user);

        return JwtAuthenticationResponseDto.builder().token(jwt).build();
    }

    @Override
    public JwtAuthenticationResponseDto signin(SigninRequestDto request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));

        var jwt = jwtService.generateToken(user);

        return JwtAuthenticationResponseDto.builder().token(jwt).build();
    }
}
