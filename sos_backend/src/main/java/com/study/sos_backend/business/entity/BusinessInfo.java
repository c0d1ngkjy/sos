package com.study.sos_backend.business.entity;


import com.study.sos_backend.business.dto.BusinessUserCreateDto;
import com.study.sos_backend.common.entity.Address;
import com.study.sos_backend.common.entity.BaseTimeEntity;
import com.study.sos_backend.common.entity.Locate;
import com.study.sos_backend.user.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Set;

@Getter
@Entity
@Table(name = "BUSINESS_INFO")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BusinessInfo extends BaseTimeEntity {

    /**
     * 미용실 정보에 대한 엔티티
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "ID", nullable = false)
    private Long id;

    /**
     * 이 사이에 미용실 정보 작성해두셍
     */


    @Column(name = "COMP_NM", nullable = false)
    private String companyName; // 상호

    @Column(name = "REPRESENT_NM", nullable = false)
    private String representativeName; // 대표자 성명

    @Column(name = "COMPANY_EMAIL")
    private String companyEmail;

    @Column(name = "COMPANY_REGISTER_NAME", nullable = false, unique = true)
    private String companyRegisterName; // 사용자 등록번호

    @OneToOne(orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "ADDRESS_ID")
    private Address address; // 주소

    @Column(name = "COMP_TEL")
    private String companyTel; // 대표 번호

    @Column(name = "LINE_INTRODUCE")
    private String lineIntroduce; // 한줄 소개

    @Column(name = "LOCATION_INFO")
    private String locationInfo; // 위치 소개

    @Column
    private String introduce; // 소개

    @Column(nullable = false)
    private String keyword; // 서비스 키워드(주차 가능, 인터넷 등)

    @DateTimeFormat(pattern = "HH:mm")
    @Column(name = "SERVICE_START_HOUR")
    private LocalTime serviceStartHour; // 영업 시작 시간

    @DateTimeFormat(pattern = "HH:mm")
    @Column(name = "SERVICE_END_HOUR")
    private LocalTime serviceEndHour; // 영업 종료 시간

    @ElementCollection(targetClass = DayOfWeek.class)
    @CollectionTable(name = "SERVICE_DAYS_OF_WEEK", joinColumns =
    @JoinColumn(name = "BUSINESS_INFO_ID")
    )
    @Enumerated(EnumType.STRING)
    @Column(name = "SERVICE_DAYS", nullable = false)
    private Set<DayOfWeek> serviceDaysOfWeek;

    // 위치 정보에 관한 엔티티
    @OneToOne(orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "LOCATE_ID")
    private Locate locate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_EMAIL")
    private User user;

    @Builder
    public BusinessInfo(String companyName, String representativeName, String companyEmail, String companyRegisterName, Address address, String companyTel, String lineIntroduce, String locationInfo, String introduce, String keyword, LocalTime serviceStartHour, LocalTime serviceEndHour, Set<DayOfWeek> serviceDaysOfWeek, Locate locate, User user) {
        this.companyName = companyName;
        this.representativeName = representativeName;
        this.companyEmail = companyEmail;
        this.companyRegisterName = companyRegisterName;
        this.address = address;
        this.companyTel = companyTel;
        this.lineIntroduce = lineIntroduce;
        this.locationInfo = locationInfo;
        this.introduce = introduce;
        this.keyword = keyword;
        this.serviceStartHour = serviceStartHour;
        this.serviceEndHour = serviceEndHour;
        this.serviceDaysOfWeek = serviceDaysOfWeek;
        this.locate = locate;
        this.user = user;
    }

    public static BusinessInfo toEntity(BusinessUserCreateDto createDto, User user){
        return BusinessInfo.builder()
                .companyName(createDto.getCompanyName())
                .representativeName(createDto.getRepresentativeName())
                .companyEmail(createDto.getCompanyEmail())
                .companyRegisterName(createDto.getCompanyRegisterName())
                .address(Address.toEntity(createDto.getAddress()))
                .companyTel(createDto.getCompanyTel())
                .lineIntroduce(createDto.getLineIntroduce())
                .locationInfo(createDto.getLocationInfo())
                .introduce(createDto.getIntroduce())
                .keyword(createDto.getKeyword())
                .serviceStartHour(createDto.getServiceStartHour())
                .serviceEndHour(createDto.getServiceEndHour())
                .serviceDaysOfWeek(createDto.getServiceDaysOfWeek())
                .locate(Locate.toEntity(createDto.getLocate()))
                .user(user)
                .build();
    }

}
