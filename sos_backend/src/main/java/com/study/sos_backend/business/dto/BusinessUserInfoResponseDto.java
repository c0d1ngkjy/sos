package com.study.sos_backend.business.dto;

public class BusinessUserInfoResponseDto {

    private String email;

    public BusinessUserInfoResponseDto(BusinessUserCreateDto createDto) {
        this.email = createDto.getEmail();
    }
}
