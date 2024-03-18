package com.study.sos_backend.business.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import com.study.sos_backend.business.entity.BusinessInfo;
import com.study.sos_backend.common.dto.AddressDto;
import com.study.sos_backend.common.dto.LocateDto;
import lombok.Data;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Set;

/**
 * DTO for {@link com.study.sos_backend.business.entity.BusinessInfo}
 */
@Data
public class BusinessInfoResponseDto {
    LocalDateTime createdDate;
    LocalDateTime modifiedDate;
    String companyName;
    String representativeName;
    String companyEmail;
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


    public BusinessInfoResponseDto(BusinessInfo businessInfo) {
        this.createdDate = businessInfo.getCreatedDate();
        this.modifiedDate = businessInfo.getModifiedDate();
        this.companyName = businessInfo.getCompanyName();
        this.representativeName = businessInfo.getRepresentativeName();
        this.companyEmail = businessInfo.getCompanyEmail();
        this.companyRegisterName = businessInfo.getCompanyRegisterName();
        this.companyTel = businessInfo.getCompanyTel();
        this.lineIntroduce = businessInfo.getLineIntroduce();
        this.locationInfo = businessInfo.getLocationInfo();
        this.introduce = businessInfo.getIntroduce();
        this.keyword = businessInfo.getKeyword();
        this.serviceStartHour = businessInfo.getServiceStartHour();
        this.serviceEndHour = businessInfo.getServiceEndHour();
        this.serviceDaysOfWeek = businessInfo.getServiceDaysOfWeek();
        this.locate = new LocateDto(businessInfo.getLocate());
        this.address = new AddressDto(businessInfo.getAddress());

    }
}