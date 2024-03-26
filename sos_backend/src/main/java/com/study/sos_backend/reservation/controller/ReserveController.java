package com.study.sos_backend.reservation.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/api/v1/reservation")
@RequiredArgsConstructor
public class ReserveController {

    // TODO 사용자가 해당 비즈니스에 예약하는 로직
    // 비즈니스에게도 알림이 가도록 해야 함

    // TODO 비즈니스가 수락, 거절하는 로직

    // TODO 비즈니스가 예약 대기 중인 사용자 확인

    // TODO 비즈니스가 모든 예약 내역 확인

}
