package com.study.sos_backend.membership.controller;

import com.study.sos_backend.business.dto.BusinessInfoResponseDto;
import com.study.sos_backend.business.dto.BusinessUpgradeRequestDto;
import com.study.sos_backend.membership.dto.BusinessMemberShipResponseDto;
import com.study.sos_backend.membership.service.MembershipService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "멤버십", description = "멤버십 관련 API")
@RestController
@RequestMapping("/api/v1/membership")
@RequiredArgsConstructor
@Slf4j
public class MembershipController {

    private final MembershipService membershipService;

    @Operation(summary = "멤버십 기간 확인", description = "비즈니스 ID의 멤버십 기간을 확인합니다.")
    @GetMapping("/business/{businessId}")
    public ResponseEntity<BusinessMemberShipResponseDto> getBusinessMemberShip(@PathVariable Long businessId){
        try{
            BusinessMemberShipResponseDto responseDto = membershipService.getMemberShip(businessId);
            return ResponseEntity.ok(responseDto);
        }catch (EntityNotFoundException e){
            return ResponseEntity.badRequest().build();
        }
    }


    // TODO 결제 모듈 수락 시 작동되도록 변경해야 함
    @Operation(summary = "비즈니스 업그레이드", description = "개월 수를 입력받고 프로로 업그레이드 합니다.")
    @PostMapping("/business")
    public ResponseEntity<BusinessInfoResponseDto> upgradePro(@RequestBody BusinessUpgradeRequestDto requestDto) {
        try {
            BusinessInfoResponseDto responseDto = membershipService.upgradeBusiness(requestDto);
            return ResponseEntity.ok(responseDto);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
