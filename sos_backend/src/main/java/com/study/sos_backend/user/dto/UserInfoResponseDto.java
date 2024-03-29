package com.study.sos_backend.user.dto;

import com.querydsl.core.annotations.QueryProjection;
import com.querydsl.core.types.dsl.EnumPath;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;
import com.study.sos_backend.user.entity.RoleType;
import com.study.sos_backend.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data

public class UserInfoResponseDto {

    private Long id;
    private String email;

    public UserInfoResponseDto(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
    }

    @QueryProjection
    public UserInfoResponseDto(Long id, String email, RoleType roleType) {
        this.id = id;
        this.email = email;
    }


}
