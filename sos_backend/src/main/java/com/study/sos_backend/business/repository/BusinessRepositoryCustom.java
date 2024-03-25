package com.study.sos_backend.business.repository;

import com.study.sos_backend.business.dto.BusinessInfoResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface BusinessRepositoryCustom {
    Page<BusinessInfoResponseDto> getAllBusiness(Pageable pageable);

    List<BusinessInfoResponseDto> findNearByBusinesses(double latitude, double longitude);
}
