package com.bank.banking.dto;

import com.bank.banking.models.Role;
import com.bank.banking.models.User;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class UserDto {

    @NotNull
    @NotEmpty
    @NotBlank
    private Integer id;

    @NotNull(message = "Le prénom ne doit pas étre null")
    @NotEmpty(message = "Le prénom ne doit pas étre null")
    @NotBlank(message = "Le prénom ne doit pas étre null")
    private String firstname;

    @NotNull
    @NotEmpty
    @NotBlank
    private String lastname;

    @NotNull
    @NotEmpty
    @NotBlank
    @Email
    private String email;

    @NotNull
    @NotEmpty
    @NotBlank
    @Size(min = 8, max = 16)
    private String password;

    //Transfert entité -> DTO
    public static UserDto fromEntity(User user) {
        return UserDto.builder()
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .email(user.getEmail())
                .password(user.getPassword())
                .build();
    }

    //Transfert DTO -> entité
    public static User toEntity(UserDto user) {
        return User.builder()
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .email(user.getEmail())
                .password(user.getPassword())
                .build();
    }
}
