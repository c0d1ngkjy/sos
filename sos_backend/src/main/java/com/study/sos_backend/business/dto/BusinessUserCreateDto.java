package com.study.sos_backend.business.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import com.study.sos_backend.common.dto.AddressDto;
import com.study.sos_backend.common.dto.LocateDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Set;

@Data
public class BusinessUserCreateDto {
    @NotEmpty
    String companyName;
    String representativeName;
    @Email
    String companyEmail;
    @NotBlank
    String companyRegisterName;
    AddressDto address;
    String companyTel;
    String lineIntroduce;
    String locationInfo;
    String introduce;
    String keyword;

    @DateTimeFormat(pattern = "HH:mm")
    @Schema(description = "서비스 시작 시간" , pattern = "HH:mm")
    @JsonFormat(pattern = "HH:mm", shape = JsonFormat.Shape.STRING, timezone = "Asia/Seoul")
    LocalTime serviceStartHour;

    @Schema(description = "서비스 마감 시간" , pattern = "HH:mm")
    @DateTimeFormat(pattern = "HH:mm")
    @JsonFormat(pattern = "HH:mm", shape = JsonFormat.Shape.STRING, timezone = "Asia/Seoul")
    LocalTime serviceEndHour;

    Set<DayOfWeek> serviceDaysOfWeek;
    LocateDto locate;
    private String email;
    private String password;
}
