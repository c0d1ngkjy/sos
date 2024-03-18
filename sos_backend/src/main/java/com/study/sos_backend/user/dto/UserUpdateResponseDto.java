package com.study.sos_backend.user.dto;

import com.study.sos_backend.user.entity.User;
import lombok.Data;

@Data
public class UserUpdateResponseDto {

    private String accessToken;
    private String refreshToken;
    private String email;

    public UserUpdateResponseDto(String accessToken, String refreshToken, User user) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.email = user.getEmail();
    }
}
