package com.kafpin.smallDelivery.dto.user;

import com.kafpin.smallDelivery.models.Role;
import lombok.Data;

@Data
public class UserDetailsDto {
    private Long id;
    private String name;
    private String surname;
    private String lastname;
    private String email;
    private Role role;

}
