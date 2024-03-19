package com.study.sos_backend.admin.controller;

import com.study.sos_backend.business.dto.BusinessInfoResponseDto;
import com.study.sos_backend.business.service.BusinessService;
import com.study.sos_backend.user.dto.UserInfoResponseDto;
import com.study.sos_backend.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


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
