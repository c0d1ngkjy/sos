package com.study.sos_backend.business.repository;

import com.study.sos_backend.business.dto.BusinessInfoResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface BusinessRepositoryCustom {
    Page<BusinessInfoResponseDto> getAllBusiness(Pageable pageable);
}
