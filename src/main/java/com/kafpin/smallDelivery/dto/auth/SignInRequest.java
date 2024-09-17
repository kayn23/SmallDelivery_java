package com.kafpin.smallDelivery.dto.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(description = "Запрос на аутентификацию")
public class SignInRequest {
    @Schema(description = "Email", example = "example@gmail.com")
    @NotBlank(message = "Email не может быть пустыми")
    private String email;
    @Schema(description = "Пароль", example = "my_1secret1_password")
    @Size(min = 6, max = 255, message = "Длина пароля должна быть от 6 до 255 символов")
    @NotBlank(message = "Пароль не может быть пустыми")
    private String password;
}
