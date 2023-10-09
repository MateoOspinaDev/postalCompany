package com.workshop.postal.Security.controller;
import com.workshop.postal.Security.services.AuthenticationService;
import com.workshop.postal.Security.dtos.SignUpRequestDto;
import com.workshop.postal.Security.dtos.SigninRequestDto;
import com.workshop.postal.Security.dtos.JwtAuthenticationResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<JwtAuthenticationResponseDto> signup(@RequestBody SignUpRequestDto request) {
        return ResponseEntity.ok(authenticationService.signup(request));
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponseDto> signin(@RequestBody SigninRequestDto request) {
        return ResponseEntity.ok(authenticationService.signin(request));
    }
}
