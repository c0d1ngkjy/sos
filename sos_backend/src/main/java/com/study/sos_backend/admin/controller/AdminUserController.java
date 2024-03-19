package com.study.sos_backend.admin.controller;


import com.study.sos_backend.user.dto.UserInfoResponseDto;
import com.study.sos_backend.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 어드민이 유저를 관리하는 컨트롤러
 */

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/admin/user")
public class AdminUserController {

    private final UserService userService;

    @Operation(summary = "유저 삭제", description = "이메일 파라미터로 유저를 삭제합니다.")
    @DeleteMapping("/{email}")
    public ResponseEntity<UserInfoResponseDto> deleteBusinessUser(@PathVariable String email){
        try{
            userService.deleteUser(email);
            return ResponseEntity.ok().build();
        } catch (EntityNotFoundException e){
            return ResponseEntity.badRequest().build();
        }
    }

}
