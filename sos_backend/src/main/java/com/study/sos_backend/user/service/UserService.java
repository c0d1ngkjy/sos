package com.study.sos_backend.user.service;

import com.study.sos_backend.auth.jwt.JwtService;
import com.study.sos_backend.business.dto.BusinessUserCreateDto;
import com.study.sos_backend.business.entity.BusinessInfo;
import com.study.sos_backend.business.repository.BusinessInfoRepository;
import com.study.sos_backend.user.dto.UserInfoResponseDto;
import com.study.sos_backend.user.dto.UserUpdateRequestDto;
import com.study.sos_backend.user.dto.UserUpdateResponseDto;
import com.study.sos_backend.user.entity.RoleType;
import com.study.sos_backend.user.entity.User;
import com.study.sos_backend.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final BusinessInfoRepository businessInfoRepository;
    private final JwtService jwtService;

    // GET
    public UserInfoResponseDto getUser(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(IllegalStateException::new);

        return new UserInfoResponseDto(user);
    }

    // TODO 일반 유저 권한에서는 사용하면 안되는 메소드
    public UserInfoResponseDto getUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(IllegalStateException::new);

        return new UserInfoResponseDto(user);
    }


    // CREATE
    @Transactional
    public void createBusinessUser(BusinessUserCreateDto createDto) {
        User user = User.builder()
                .email(createDto.getEmail())
                .roleType(RoleType.BUSINESS)
                .password(createDto.getPassword())
                .build();

        BusinessInfo businessInfo = BusinessInfo.toEntity(createDto, user);

        userRepository.save(user);
        businessInfoRepository.save(businessInfo);

    }

    // UPDATE
    @Transactional
    public UserUpdateResponseDto updateUser(String email, UserUpdateRequestDto requestDto){
        if (userRepository.existsAllByEmail(requestDto.getEmail())){
            throw new IllegalStateException();
        }

        User user = userRepository.findByEmail(email).orElseThrow(IllegalStateException::new);

        // 새로운 이메일로 토큰을 만듦니다.
        String accessToken = jwtService.createAccessToken(requestDto.getEmail());
        String refreshToken = jwtService.createRefreshToken();

        user.update(requestDto);
        user.updateRefreshToken(refreshToken);

        userRepository.save(user);

        return new UserUpdateResponseDto(accessToken, refreshToken, user);
    }

    public boolean verifyEmail(String email){
        return !userRepository.existsAllByEmail(email);
    }
}
