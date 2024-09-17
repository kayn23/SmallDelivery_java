package com.kafpin.smallDelivery.controllers;

import com.kafpin.smallDelivery.dto.auth.JwtAuthenticationResponse;
import com.kafpin.smallDelivery.dto.auth.SignInRequest;
import com.kafpin.smallDelivery.dto.auth.SignUpRequest;
import com.kafpin.smallDelivery.services.AuthenticationService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Аутентификация")
public class AuthController {
  private final AuthenticationService authenticationService;

  @Operation(summary = "Регистрация пользователя")
  @PostMapping("/sing-up")
  public JwtAuthenticationResponse signUp(@RequestBody SignUpRequest request) {
    return authenticationService.signUp(request);
  }

  @Operation(summary = "Авторизация пользователя")
  @PostMapping("/sign-in")
  public JwtAuthenticationResponse signIn(@RequestBody SignInRequest request) {
    return authenticationService.signIn(request);
  }
}
