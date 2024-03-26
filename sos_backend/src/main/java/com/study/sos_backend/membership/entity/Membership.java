package com.study.sos_backend.membership.entity;

import com.study.sos_backend.business.entity.Business;
import com.study.sos_backend.membership.enums.MembershipType;
import com.study.sos_backend.common.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "MEMBERSHIP")
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Membership extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @OneToOne
    @JoinColumn(name ="BUSINESS_INFO_ID")
    private Business business;

    @Enumerated(EnumType.STRING)
    private MembershipType type;

    @Column(name = "START_DATE_TIME")
    private LocalDateTime startDateTime; // 시작일자

    @Column(name = "END_START_TIME")
    private LocalDateTime endDateTime; // 종료 일자

    public Membership(Business business) {
        this.business = business;
        this.type = MembershipType.FREE;
    }

    public void changeProBusiness(int months) {
        this.type = MembershipType.PRO;
        this.startDateTime = LocalDateTime.now();
        this.endDateTime = startDateTime.plusMonths(months);
    }

    public void changeFreeType(){
        this.type = MembershipType.FREE;
    }
}
