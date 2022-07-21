package com.example.springshop.process.domain.user.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class MailDto {
    private String address;
    private String title;
    private String message;

    public void setEmailContents(String address, String title, String message) {
        this.address = address;
        this.title = title;
        this.message = message;
    }
}
