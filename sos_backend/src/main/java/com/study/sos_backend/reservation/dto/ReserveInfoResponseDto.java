package com.study.sos_backend.reservation.dto;

import com.study.sos_backend.reservation.entity.type.ReserveStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReserveInfoResponseDto {

    private String email;

    private Long businessId;

    private String companyName;

    private String companyTel;

    private LocalDateTime dateTime;

    private ReserveStatus status;
}
