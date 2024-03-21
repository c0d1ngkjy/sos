package com.study.sos_backend.admin.controller;


import com.study.sos_backend.user.dto.UserInfoResponseDto;
import com.study.sos_backend.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * 어드민이 유저를 관리하는 컨트롤러
 */

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/admin/user")
public class AdminUserController {

    private final UserService userService;

    // TODO 모든 유저 정보 확인
    @GetMapping
    public ResponseEntity<Page<UserInfoResponseDto>> getUsers(Pageable pageable){
        try{
            return ResponseEntity.ok(userService.getUsers(pageable));
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "ID 사용자 정보 반환(테스트용, 삭제하거나 어드민 이상만)", description = "ID 파라미터를 통해 사용자의 유저 정보를 반환합니다.")
    public ResponseEntity<UserInfoResponseDto> getUserInfo(@PathVariable Long id) {
        try {
            UserInfoResponseDto user = userService.getUser(id);
            return ResponseEntity.ok(user);
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().build();
        }
    }

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
