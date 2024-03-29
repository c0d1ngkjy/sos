package com.study.sos_backend.business.repository;

import com.study.sos_backend.business.entity.hair.HairPrice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HairPriceRepository extends JpaRepository<HairPrice, Long> {
}
