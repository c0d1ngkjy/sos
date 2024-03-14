package com.study.sos_backend.business.entity;

import com.study.sos_backend.common.entity.Address;
import com.study.sos_backend.common.entity.BaseTimeEntity;
import com.study.sos_backend.common.entity.Locate;
import com.study.sos_backend.user.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
     *  이 사이에 미용실 정보 작성해두셍
     */

    @Column(name = "COMP_NM", nullable = false)
    private String companyName; // 상호

    @Column(name = "REPRESENT_NM", nullable = false)
    private String representativeName; // 대표자 성명

    private String email;

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

    @Column(name = "SERVICE_START_HOUR")
    private LocalTime serviceStartHour; // 영업 시작 시간

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
}
