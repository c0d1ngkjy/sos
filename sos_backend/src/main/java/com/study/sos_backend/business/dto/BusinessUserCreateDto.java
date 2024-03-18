package com.study.sos_backend.business.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import com.study.sos_backend.common.dto.AddressDto;
import com.study.sos_backend.common.dto.LocateDto;
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
    private String email;

    private String password;

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

    @JsonSerialize(using = LocalTimeSerializer.class)
    @JsonFormat(pattern="HH:mm")
    LocalTime serviceStartHour;

    @JsonSerialize(using = LocalTimeSerializer.class)
    @JsonFormat(pattern="HH:mm")
    LocalTime serviceEndHour;

    Set<DayOfWeek> serviceDaysOfWeek;

    LocateDto locate;
}
