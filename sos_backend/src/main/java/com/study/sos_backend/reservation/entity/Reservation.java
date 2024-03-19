package com.study.sos_backend.reservation.entity;

import com.study.sos_backend.business.entity.Business;
import com.study.sos_backend.common.entity.BaseTimeEntity;
import com.study.sos_backend.reservation.entity.type.ReserveStatus;
import com.study.sos_backend.user.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Reservation extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_EMAIL", referencedColumnName = "EMAIL")
    private User user;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BUSINESS_INFO_ID")
    private Business business;

    @Enumerated(EnumType.STRING)
    private ReserveStatus status;

    public Reservation(User user, Business business) {
        this.user = user;
        this.business = business;
        this.status = ReserveStatus.PENDING;
    }

    public void updateStatus(ReserveStatus status) {
        this.status = status;
    }
}
