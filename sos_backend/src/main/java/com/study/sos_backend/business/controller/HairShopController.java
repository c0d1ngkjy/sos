package com.study.sos_backend.business.controller;

import com.study.sos_backend.business.dto.HairDesignerInfoCreateRequestDto;
import com.study.sos_backend.business.dto.HairDesignerInfoResponseDto;
import com.study.sos_backend.business.service.HairShopService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@Tag(name = "헤어샵 API", description = "헤어샵 설정 관련 API")
@RestController
@RequestMapping("/api/v1/business/hair_shop")
@RequiredArgsConstructor
@Slf4j
public class HairShopController {

    private final HairShopService hairShopService;

    @PreAuthorize("hasRole('ROLE_BUSINESS')")
    @PostMapping("/designer")
    @Operation(summary = "디자이너 추가", description = "디자이너를 추가합니다.")
    public ResponseEntity<HairDesignerInfoResponseDto> addDesigner(@RequestBody HairDesignerInfoCreateRequestDto requestDto){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        try{
            HairDesignerInfoResponseDto responseDto = hairShopService.addDesigner(email, requestDto);
            return ResponseEntity.ok(responseDto);
        }catch (EntityNotFoundException e){
            return ResponseEntity.badRequest().build();
        }

    }
}
