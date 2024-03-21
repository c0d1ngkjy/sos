package com.study.sos_backend.user.repository;

import com.study.sos_backend.user.dto.UserInfoResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserRepositoryCustom {
    Page<UserInfoResponseDto> getUsers(Pageable pageable);
    Optional<UserInfoResponseDto> getBusinessOwner(Long id);

}
