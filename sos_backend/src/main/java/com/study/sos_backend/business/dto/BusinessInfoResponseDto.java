package com.study.sos_backend.business.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.querydsl.core.annotations.QueryProjection;
import com.study.sos_backend.business.entity.Business;
import com.study.sos_backend.business.entity.hair.HairDesigner;
import com.study.sos_backend.common.dto.AddressDto;
import com.study.sos_backend.common.dto.LocateDto;
import lombok.Data;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * DTO for {@link Business}
 */
@Data
public class BusinessInfoResponseDto {
    private String introduce;
    private Long id;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private String companyName;
    private String companyRegisterName;
    private String representativeName;
    private AddressDto address;
    private String companyTel;
    private String lineIntroduce;
    private String locationInfo;
    private String companyEmail;
    private String keyword;
    @JsonDeserialize(using = LocalTimeDeserializer.class)
    @JsonFormat(pattern = "HH:mm", shape = JsonFormat.Shape.STRING, timezone = "Asia/Seoul")
    private LocalTime serviceStartHour;
    @JsonDeserialize(using = LocalTimeDeserializer.class)
    @JsonFormat(pattern = "HH:mm", shape = JsonFormat.Shape.STRING, timezone = "Asia/Seoul")
    private LocalTime serviceEndHour;
    private Set<DayOfWeek> serviceDaysOfWeek;
    private LocateDto locate;
    private Double distance;
    private List<HairDesignerInfoResponseDto> designerInfos;

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
        this.designerInfos = changeDesignerInfoDto(business.getHairDesigners());
    }

    @QueryProjection
    public BusinessInfoResponseDto(Business business, Double distance) {
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
        if (business.getLocate() != null){
            this.locate = new LocateDto(business.getLocate());
        }
        if (business.getAddress() != null){
            this.address = new AddressDto(business.getAddress());
        }
        this.distance = distance;
    }

    private List<HairDesignerInfoResponseDto> changeDesignerInfoDto(List<HairDesigner> designerList) {
        return Optional
                .of(designerList
                        .stream()
                        .map(HairDesignerInfoResponseDto::new)
                        .toList())
                .orElse(Collections.emptyList());
    }
}