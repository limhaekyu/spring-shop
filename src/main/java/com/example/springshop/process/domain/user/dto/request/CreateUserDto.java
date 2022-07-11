package com.example.springshop.process.domain.user.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class CreateUserDto {
    private String userName;

    private String email;

    private String password;

    private String phoneNumber;
}
