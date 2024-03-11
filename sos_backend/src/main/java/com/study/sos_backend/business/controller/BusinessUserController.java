package com.study.sos_backend.business.controller;

import com.study.sos_backend.business.dto.BusinessUserCreateDto;
import com.study.sos_backend.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/business")
@RequiredArgsConstructor
@Slf4j
public class BusinessUserController {

    private final UserService userService;

    @PostMapping("/sign-up")
    public ResponseEntity<?> createBusiness(@RequestBody BusinessUserCreateDto createDto) {
        try {
            userService.createBusinessUser(createDto);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
