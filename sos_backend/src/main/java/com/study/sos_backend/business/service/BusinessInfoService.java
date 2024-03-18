package com.study.sos_backend.business.service;

import com.study.sos_backend.business.dto.BusinessInfoResponseDto;
import com.study.sos_backend.business.entity.BusinessInfo;
import com.study.sos_backend.business.repository.BusinessInfoRepository;
import com.study.sos_backend.user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BusinessInfoService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final BusinessInfoRepository businessInfoRepository;

    public BusinessInfoResponseDto getBusinessInfo(Long id) {
        BusinessInfo businessInfo = businessInfoRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        return new BusinessInfoResponseDto(businessInfo);
    }

    public List<BusinessInfoResponseDto> getAllByUser(String email) {
        List<BusinessInfo> businessInfoList = userRepository.findByEmail(email).orElseThrow(IllegalArgumentException::new).getBusinessInfoList();

        if (businessInfoList.isEmpty()) {
            throw new EntityNotFoundException();
        }

        return businessInfoList.stream().map(BusinessInfoResponseDto::new).toList();
    }


}
