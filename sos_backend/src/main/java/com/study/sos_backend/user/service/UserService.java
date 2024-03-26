package com.study.sos_backend.user.service;

import com.study.sos_backend.auth.jwt.JwtService;
import com.study.sos_backend.business.dto.BusinessUserCreateDto;
import com.study.sos_backend.business.entity.Business;
import com.study.sos_backend.business.repository.BusinessRepository;
import com.study.sos_backend.business.service.BusinessService;
import com.study.sos_backend.user.dto.UserInfoResponseDto;
import com.study.sos_backend.user.dto.UserUpdateRequestDto;
import com.study.sos_backend.user.dto.UserUpdateResponseDto;
import com.study.sos_backend.user.entity.RoleType;
import com.study.sos_backend.user.entity.User;
import com.study.sos_backend.user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final BusinessRepository businessRepository;
    private final JwtService jwtService;
    private final BusinessService businessService;

    // GET
    public UserInfoResponseDto getUser(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(IllegalStateException::new);

        return new UserInfoResponseDto(user);
    }

    /**
     * 일반 유저 사용 금지
     * @param id 아이디
     * @return UserInfoResponseDto
     */
    public UserInfoResponseDto getUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(IllegalStateException::new);

        return new UserInfoResponseDto(user);
    }

    public Page<UserInfoResponseDto> getUsers(Pageable pageable){
        return userRepository.getUsers(pageable);
    }

    public UserInfoResponseDto getBusinessOwner(Long id){
        return userRepository.getBusinessOwner(id).orElseThrow(EntityNotFoundException::new);
    }

    // CREATE
    @Transactional
    public void createBusinessUser(BusinessUserCreateDto createDto) {
        User user = User.builder()
                .email(createDto.getEmail())
                .roleType(RoleType.BUSINESS)
                .password(createDto.getPassword())
                .build();

        Business business = Business.toEntity(createDto, user);
        userRepository.save(user);
        businessRepository.save(business);

    }

    // UPDATE
    @Transactional
    public UserUpdateResponseDto updateUser(String email, UserUpdateRequestDto requestDto) {
        if (userRepository.existsAllByEmail(requestDto.getEmail())) {
            throw new IllegalStateException();
        }

        User user = userRepository.findByEmail(email).orElseThrow(EntityNotFoundException::new);

        // 새로운 이메일로 토큰을 만듦니다.
        String accessToken = jwtService.createAccessToken(requestDto.getEmail());
        String refreshToken = jwtService.createRefreshToken();

        user.update(requestDto);
        user.updateRefreshToken(refreshToken);

        userRepository.save(user);

        return new UserUpdateResponseDto(accessToken, refreshToken, user);
    }

    // DELETE
    @Transactional
    public void deleteUser(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(EntityNotFoundException::new);
        userRepository.delete(user);
    }

    @Transactional
    public void deleteBusinessUser(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(EntityNotFoundException::new);
        businessRepository.deleteAllByOwner(user);
        userRepository.delete(user);
    }

    public boolean verifyEmail(String email) {
        return !userRepository.existsAllByEmail(email);
    }
}
