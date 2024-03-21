package com.study.sos_backend.business.repository.impl;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.study.sos_backend.business.dto.BusinessInfoResponseDto;
import com.study.sos_backend.business.entity.QBusiness;
import com.study.sos_backend.business.repository.BusinessRepositoryCustom;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Objects;


public class BusinessRepositoryCustomImpl implements BusinessRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public BusinessRepositoryCustomImpl(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public Page<BusinessInfoResponseDto> getAllBusiness(Pageable pageable) {
        QBusiness business = QBusiness.business;

        List<BusinessInfoResponseDto> results = queryFactory
                .select(Projections.bean(BusinessInfoResponseDto.class, business))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long size = Objects.requireNonNull(queryFactory.select(business.count()).from(business).fetchOne());

        return new PageImpl<>(results, pageable, size);
    }
}
