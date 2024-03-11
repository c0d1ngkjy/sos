package com.study.sos_backend.user.service;

import com.study.sos_backend.business.dto.BusinessUserCreateDto;
import com.study.sos_backend.user.entity.RoleType;
import com.study.sos_backend.user.entity.User;
import com.study.sos_backend.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    public void createBusinessUser(BusinessUserCreateDto createDto) {
        User user = User.builder()
                .email(createDto.getEmail())
                .roleType(RoleType.BUSINESS)
                .password(createDto.getPassword())
                .build();

        userRepository.save(user);
    }
}
