package com.example.springshop.process.domain.user.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class FindUserEmailDto {
    private String userName;

    private String phoneNumber;
}
