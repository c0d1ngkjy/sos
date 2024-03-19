package com.study.sos_backend.business.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.study.sos_backend.business.entity.Business;
import com.study.sos_backend.common.dto.AddressDto;
import com.study.sos_backend.common.dto.LocateDto;
import lombok.Data;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Set;

/**
 * DTO for {@link Business}
 */
@Data
public class BusinessInfoResponseDto {
    Long id;
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
    @JsonDeserialize(using = LocalTimeDeserializer.class)
    @JsonFormat(pattern = "HH:mm", shape = JsonFormat.Shape.STRING, timezone = "Asia/Seoul")
    LocalTime serviceStartHour;
    @JsonDeserialize(using = LocalTimeDeserializer.class)
    @JsonFormat(pattern = "HH:mm", shape = JsonFormat.Shape.STRING, timezone = "Asia/Seoul")
    LocalTime serviceEndHour;
    Set<DayOfWeek> serviceDaysOfWeek;
    LocateDto locate;


    public BusinessInfoResponseDto(Business business) {
        this.id = business.getId();
        this.createdDate = business.getCreatedDate();
        this.modifiedDate = business.getModifiedDate();
        this.companyName = business.getCompanyName();
        this.representativeName = business.getRepresentativeName();
        this.companyEmail = business.getCompanyEmail();
        this.companyRegisterName = business.getCompanyRegisterName();
        this.companyTel = business.getCompanyTel();
        this.lineIntroduce = business.getLineIntroduce();
        this.locationInfo = business.getLocationInfo();
        this.introduce = business.getIntroduce();
        this.keyword = business.getKeyword();
        this.serviceStartHour = business.getServiceStartHour();
        this.serviceEndHour = business.getServiceEndHour();
        this.serviceDaysOfWeek = business.getServiceDaysOfWeek();
        this.locate = new LocateDto(business.getLocate());
        this.address = new AddressDto(business.getAddress());

    }
}