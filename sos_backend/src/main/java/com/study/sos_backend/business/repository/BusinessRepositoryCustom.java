package com.study.sos_backend.business.repository;

import com.study.sos_backend.business.dto.BusinessInfoResponseDto;
import com.study.sos_backend.business.entity.Business;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;


public interface BusinessRepositoryCustom {

    Optional<BusinessInfoResponseDto> getBusinessInfoById(Long id);

    Page<BusinessInfoResponseDto> getAllBusiness(Pageable pageable);

    List<BusinessInfoResponseDto> findNearByBusinesses(double latitude, double longitude);

    Optional<Business> findByOwnerEmailAndId(Long id, String email);
}
