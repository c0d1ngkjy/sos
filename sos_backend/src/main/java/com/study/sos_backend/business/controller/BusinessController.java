package com.study.sos_backend.business.controller;

import com.study.sos_backend.business.dto.BusinessInfoResponseDto;
import com.study.sos_backend.business.dto.BusinessUserCreateDto;
import com.study.sos_backend.business.dto.BusinessUserInfoResponseDto;
import com.study.sos_backend.business.service.BusinessInfoService;
import com.study.sos_backend.user.dto.UserInfoResponseDto;
import com.study.sos_backend.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/business")
@RequiredArgsConstructor
@Slf4j
public class BusinessController {

    private final UserService userService;
    private final BusinessInfoService businessInfoService;



    @GetMapping("/{id}")
    @Operation(summary = "비즈니스 ID 정보 확인", description = "ID 파라미터로 비즈니스 정보를 확인합니다.")
    public ResponseEntity<BusinessInfoResponseDto> getBusinessInfo(@PathVariable Long id) {
        try {
            BusinessInfoResponseDto businessInfo = businessInfoService.getBusinessInfo(id);
            return ResponseEntity.ok(businessInfo);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/login")
    @Operation(summary = "비즈니스 로그인", description = "비즈니스 로그인 실행")
    public void login(@RequestBody LoginDto loginDto) {
        log.info(String.valueOf(loginDto));
    }

    @Operation(summary = "비즈니스 계정 생성", description = "비즈니스 계정을 생성합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "유저 정보 반환", content = @Content(schema = @Schema(implementation = BusinessUserCreateDto.class))),
            @ApiResponse(responseCode = "400", description = "요청 매개변수 누락")
    })
    @PostMapping("/sign-up")
    public ResponseEntity<BusinessUserInfoResponseDto> createBusiness(@RequestBody BusinessUserCreateDto createDto) {
        try {
            userService.createBusinessUser(createDto);
            return ResponseEntity.ok(new BusinessUserInfoResponseDto(createDto));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }


    @Data
    private static class LoginDto {
        @Email
        private String email;

        @NotNull
        private String password;
    }


}
