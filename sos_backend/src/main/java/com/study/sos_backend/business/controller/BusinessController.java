package com.study.sos_backend.business.controller;

import com.study.sos_backend.business.dto.BusinessInfoResponseDto;
import com.study.sos_backend.business.dto.BusinessInfoUpdateRequestDto;
import com.study.sos_backend.business.dto.BusinessUserCreateDto;
import com.study.sos_backend.business.dto.BusinessUserInfoResponseDto;
import com.study.sos_backend.business.enums.BusinessSortType;
import com.study.sos_backend.business.service.BusinessService;
import com.study.sos_backend.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(name = "비즈니스", description = "비즈니스 관련 API")
@RestController
@RequestMapping("/api/v1/business")
@RequiredArgsConstructor
@Slf4j
public class BusinessController {

    private final UserService userService;
    private final BusinessService businessService;


    @GetMapping("/{id}")
    @Operation(summary = "비즈니스 ID 정보 확인", description = "ID 파라미터로 비즈니스 정보를 확인합니다.")
    public ResponseEntity<BusinessInfoResponseDto> getBusinessInfo(@PathVariable Long id) {
        try {
            BusinessInfoResponseDto businessInfo = businessService.getBusinessInfo(id);
            return ResponseEntity.ok(businessInfo);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/find")
    @Operation(summary = "내 주변 비즈니스 확인", description = "위도 경도 파라미터로 주변 5KM 반경 내에 비즈니스 반환. 이때, sort 를 통해 거리순, 가격 순 반환")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "비즈니스 정보 반환", content = @Content(schema = @Schema(implementation = BusinessUserCreateDto.class))),
            @ApiResponse(responseCode = "400", description = "매개 변수가 잘못되거나 서버 오류"),
            @ApiResponse(responseCode = "404", description = "주변에 가까운 비즈니스 정보가 존재하지 않음")
    })
    public ResponseEntity<List<BusinessInfoResponseDto>> getNearbyBusinessInfos(@RequestParam(required = true) Double latitude, @RequestParam(required = true) Double longitude, @RequestParam(required = false) BusinessSortType sortType) {

        try{
            if (sortType == null || sortType == BusinessSortType.DISTANCE)
                sortType = BusinessSortType.DISTANCE;
            else{
                sortType = BusinessSortType.PRICE;
            }
            List<BusinessInfoResponseDto> businessInfos = businessService.getNearbyBusinessInfos(latitude, longitude, sortType);
        }catch (EmptyResultDataAccessException e){
            return ResponseEntity.notFound().build();
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
        return null;
    }

    @PreAuthorize("hasRole('ROLE_BUSINESS')")
    @GetMapping()
    @Operation(summary = "사용자의 비즈니스 정보 모두 반환", description = "비즈니스 사용자의 모든 비즈니스 정보를 반환합니다.")
    public ResponseEntity<List<BusinessInfoResponseDto>> getAllBusinessInfos() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        try {
            List<BusinessInfoResponseDto> infoResponseDtos = businessService.getAllByUser(email);
            return ResponseEntity.ok(infoResponseDtos);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().build();
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // TODO 비즈니스 추가

    @PreAuthorize("hasRole('ROLE_BUSINESS')")
    @PutMapping("/{id}")
    @Operation(summary = "비즈니스 정보 수정", description = "비즈니스 유저가 자신의 비즈니스 정보를 수정합니다. 이때, 해당 비즈니스 관리자가 아니면 거부합니다.")
    public ResponseEntity<BusinessInfoResponseDto> updateBusinessInfo(@PathVariable Long id, @RequestBody BusinessInfoUpdateRequestDto updateRequestDto) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        try {
            BusinessInfoResponseDto responseDto = businessService.updateBusinessInfo(id, email, updateRequestDto);
            return ResponseEntity.ok(responseDto);
        } catch (IllegalAccessException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        } catch (EntityNotFoundException e) {
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
            return ResponseEntity.ok().build();
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
