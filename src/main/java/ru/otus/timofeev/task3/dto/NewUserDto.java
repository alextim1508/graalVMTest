package ru.otus.timofeev.task3.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "password")
public class NewUserDto {

    @NotNull(message = "{user.name.not_null}")
    @Size(max = 255, message = "{user.name.length}")
    @NotBlank(message = "{user.name.not_blank}")
    private String name;

    @NotNull(message = "{user.email.not_null}")
    @Email(message = "{user.email.not_valid}")
    @Size(max = 255, message = "{user.email.length}")
    @NotBlank(message = "{user.email.not_blank}")
    private String email;

    @NotNull(message = "{user.password.not_null}")
    @Size(max = 255, message = "{user.pass.length}")
    @NotBlank(message = "{user.pass.not_blank}")
    private String password;
}

