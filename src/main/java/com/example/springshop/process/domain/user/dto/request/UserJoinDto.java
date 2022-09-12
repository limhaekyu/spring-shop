package com.example.springshop.process.domain.user.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserJoinDto {
    private String email;

    private String password;

    @JsonProperty("userName")
    private String userName;

    @JsonProperty("phoneNumber")
    private String phoneNumber;
}
