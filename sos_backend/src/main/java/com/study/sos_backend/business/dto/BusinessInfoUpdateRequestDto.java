package com.study.sos_backend.business.dto;

import com.study.sos_backend.common.dto.AddressDto;
import com.study.sos_backend.common.dto.LocateDto;
import lombok.Data;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Set;

@Data
public class BusinessInfoUpdateRequestDto {
    private String companyName; // 상호
    private String representativeName; // 대표자 성명
    private String companyEmail;
    private String companyRegisterName; // 사용자 등록번호
    private AddressDto address; // 주소
    private String companyTel; // 대표 번호
    private String lineIntroduce; // 한줄 소개
    private String locationInfo; // 위치 소개
    private String introduce; // 소개
    private String keyword; // 서비스 키워드(주차 가능, 인터넷 등)
    private LocalTime serviceStartHour; // 영업 시작 시간
    private LocalTime serviceEndHour; // 영업 종료 시간
    private Set<DayOfWeek> serviceDaysOfWeek;
    private LocateDto locate;
}
