package com.study.sos_backend.business.repository;

import com.study.sos_backend.business.entity.BusinessInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusinessInfoRepository extends JpaRepository<BusinessInfo, Long> {

}
