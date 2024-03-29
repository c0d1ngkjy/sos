package com.study.sos_backend.reservation.dto;

import com.study.sos_backend.reservation.entity.type.ReserveStatus;
import com.study.sos_backend.user.dto.UserInfoResponseDto;
import lombok.Data;

import java.time.LocalDateTime;


@Data
public class ReserveUserInfoResponseDto {

    UserInfoResponseDto userInfo;

    ReserveStatus status;

    LocalDateTime reserveTime;
}
