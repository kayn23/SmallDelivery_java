package com.kafpin.smallDelivery.controllers;

import com.kafpin.smallDelivery.dto.auth.JwtAuthenticationResponse;
import com.kafpin.smallDelivery.dto.auth.SignInRequest;
import com.kafpin.smallDelivery.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationService authenticationService;

    @PostMapping("/sing-up")
    public JwtAuthenticationResponse signUp(@RequestBody SignInRequest request) {
        return authenticationService.signUp(request);
    }
    @PostMapping("/sign-in")
    public JwtAuthenticationResponse signIn(@RequestBody SignInRequest request) {
        return authenticationService.signIn(request);
    }
}
