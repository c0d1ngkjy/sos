package com.study.sos_backend.business.service;

import com.study.sos_backend.business.dto.BusinessInfoResponseDto;
import com.study.sos_backend.business.dto.BusinessInfoUpdateRequestDto;
import com.study.sos_backend.business.entity.Business;
import com.study.sos_backend.business.enums.BusinessSortType;
import com.study.sos_backend.business.repository.BusinessRepository;
import com.study.sos_backend.user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BusinessService {

    private final UserRepository userRepository;
    private final BusinessRepository businessRepository;

    public BusinessInfoResponseDto getBusinessInfo(Long id) {
        Business business = businessRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return new BusinessInfoResponseDto(business);
    }

    public Page<BusinessInfoResponseDto> getBusinessInfos(Pageable pageable) {
        return businessRepository.getAllBusiness(pageable);
    }

    public List<BusinessInfoResponseDto> getNearbyBusinessInfos(double latitude, double longitude, BusinessSortType sortType){

        List<BusinessInfoResponseDto> responseDtos = businessRepository.findNearByBusinesses(latitude, longitude);
        if (responseDtos.isEmpty()){
            throw new EmptyResultDataAccessException(0);
        }

        return responseDtos;
    }

    public List<BusinessInfoResponseDto> getAllByUser(String email) {
        List<Business> businessList = userRepository.findByEmail(email).orElseThrow(EntityNotFoundException::new).getBusinessList();

        if (businessList.isEmpty()) {
            throw new EmptyResultDataAccessException(0);
        }

        return businessList.stream().map(BusinessInfoResponseDto::new).toList();
    }

    // 비즈니스 정보 변경
    @Transactional
    public BusinessInfoResponseDto updateBusinessInfo(Long id, String email, BusinessInfoUpdateRequestDto updateRequestDto) throws IllegalAccessException {
        Business business = businessRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        if (business.getOwner().getEmail().equals(email)) {
            business.update(updateRequestDto);
            return new BusinessInfoResponseDto(businessRepository.save(business));
        }

        throw new IllegalAccessException();
    }

    // 비즈니스 삭제
    @Transactional
    public void deleteBusiness(Business business) {
        businessRepository.delete(business);
    }


    // ID 파라미터 비즈니스 삭제
    @Transactional
    public void deleteByBusinessId(Long id) throws EmptyResultDataAccessException {
        businessRepository.deleteById(id);
    }


}
