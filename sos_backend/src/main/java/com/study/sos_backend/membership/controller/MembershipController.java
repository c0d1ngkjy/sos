package com.study.sos_backend.membership.controller;

import com.study.sos_backend.business.dto.BusinessInfoResponseDto;
import com.study.sos_backend.business.dto.BusinessUpgradeRequestDto;
import com.study.sos_backend.business.dto.BusinessUserCreateDto;
import com.study.sos_backend.membership.dto.BusinessMemberShipResponseDto;
import com.study.sos_backend.membership.service.MembershipService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "멤버십", description = "멤버십 관련 API")
@RestController
@RequestMapping("/api/v1/membership")
@RequiredArgsConstructor
@Slf4j
public class MembershipController {

    private final MembershipService membershipService;



    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "멤버십 정보 반환", content = @Content(schema = @Schema(implementation = BusinessMemberShipResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "매개 변수가 잘못되거나 서버 오류"),
            @ApiResponse(responseCode = "404", description = "주변에 가까운 비즈니스 정보가 존재하지 않음")
    })
    @Operation(summary = "멤버십 기간 확인", description = "비즈니스 ID의 멤버십 기간을 확인합니다.")
    @GetMapping("/business/{businessId}")
    public ResponseEntity<BusinessMemberShipResponseDto> getBusinessMemberShip(@PathVariable Long businessId){
        try{
            BusinessMemberShipResponseDto responseDto = membershipService.getMemberShip(businessId);
            return ResponseEntity.ok(responseDto);
        }catch (EntityNotFoundException e){
            return ResponseEntity.badRequest().build();
        } catch (IllegalAccessException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
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
