package com.study.sos_backend.user.dto;

import com.study.sos_backend.user.entity.User;
import lombok.Data;

@Data
public class UserInfoResponseDto {

    private String email;

    public UserInfoResponseDto(User user) {
        this.email = user.getEmail();
    }
}
