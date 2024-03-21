package com.study.sos_backend.admin.controller;

import com.study.sos_backend.business.dto.BusinessInfoResponseDto;
import com.study.sos_backend.business.service.BusinessService;
import com.study.sos_backend.user.dto.UserInfoResponseDto;
import com.study.sos_backend.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * 어드민이 비즈니스를 관리하는 컨트롤러
 */

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/admin/business")
public class AdminBusinessController {

    private final BusinessService businessService;
    private final UserService userService;


    // TODO 모든 비즈니스 정보를 반환하는 메소드 제작하기 (페이징 처리)
    @Operation(summary = "모든 비즈니스 정보 확인", description = "페이징을 이용해 모든 비즈니스 정보를 확인합니다.")
    @GetMapping
    public ResponseEntity<Page<BusinessInfoResponseDto>> getBusinessInfos(Pageable pageable){
        try{
            Page<BusinessInfoResponseDto> businessInfos = businessService.getBusinessInfos(pageable);
            return ResponseEntity.ok(businessInfos);
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    // TODO 비즈니스 회원 정보 확인
    @Operation(summary = "비즈니스 소유자 확인", description = "해당 비즈니스의 소유자를 확인합니다.")
    @GetMapping("{id}/owner")
    public ResponseEntity<UserInfoResponseDto> businessOwner(@PathVariable Long id){
        try{
            UserInfoResponseDto responseDto = userService.getBusinessOwner(id);
            return ResponseEntity.ok(responseDto);
        }catch (EntityNotFoundException e){
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(summary = "비즈니스 정보 삭제", description = "ID 파라미터로 비즈니스 정보를 삭제합니다.")
    @DeleteMapping("/info/{id}")
    public ResponseEntity<BusinessInfoResponseDto> deleteBusinessInfo(@PathVariable Long id) {
        try {
            businessService.deleteByBusinessId(id);
            return ResponseEntity.ok().build();
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(summary = "비즈니스 유저 삭제", description = "email 파라미터로 비즈니스 유저를 삭제합니다.")
    @DeleteMapping("/user/{email}")
    public ResponseEntity<UserInfoResponseDto> deleteBusinessUser(@PathVariable String email){
        try{
            userService.deleteBusinessUser(email);
            return ResponseEntity.ok().build();
        } catch (EntityNotFoundException e){
            return ResponseEntity.badRequest().build();
        }
    }

}
