package com.kafpin.smallDelivery.dto.auth;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class SignUpRequest {
    private String name;
    private String surname;
    private String lastname;
    private String email;
    private String password;
}
