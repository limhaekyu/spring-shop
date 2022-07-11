package com.example.springshop.process.domain.user.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UpdateUserInfoDto {
    private String userName;

    private String password;

    private String phoneNumber;
}
