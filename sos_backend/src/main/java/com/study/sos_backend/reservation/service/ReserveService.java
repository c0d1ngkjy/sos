package com.study.sos_backend.reservation.service;

import com.study.sos_backend.reservation.dto.ReserveInfoResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReserveService {

    // TODO 비즈니스가 예약 내역 리스트 확인 pageable

    // TODO 유저가 자신의 예약 내역 리스트 확인 Pageable

    // TODO 사용자가 해당 비즈니스에 예약하는 로직
    // 비즈니스에게도 알림이 가도록 해야 함
    public ReserveInfoResponseDto reserve(String email, Long businessId){
        return null;
    }


    // TODO 비즈니스가 수락, 거절하는 로직

    public ReserveInfoResponseDto accept(){
        return null;
    }

    public ReserveInfoResponseDto denied(){
        return null;
    }

    // TODO 비즈니스가 예약 대기 중인 사용자 확인

    // TODO 비즈니스가 모든 예약 내역 확인

}
