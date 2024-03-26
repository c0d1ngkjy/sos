package com.study.sos_backend.reservation.entity;

import com.study.sos_backend.business.entity.Business;
import com.study.sos_backend.common.entity.BaseTimeEntity;
import com.study.sos_backend.reservation.entity.type.ReserveStatus;
import com.study.sos_backend.user.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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

    @Column(name = "RESERVATION_TIME", nullable = false)
    private LocalDateTime reservation_time;

    @Enumerated(EnumType.STRING)
    private ReserveStatus status;

    @Builder
    public Reservation(User user, Business business, LocalDateTime dateTime) {
        this.user = user;
        this.business = business;
        this.reservation_time = dateTime;
        this.status = ReserveStatus.PENDING;
    }

    public void updateStatus(ReserveStatus status) {
        this.status = status;
    }

    public void updateDateTime(LocalDateTime dateTime){
        this.reservation_time = dateTime;
    }
}
