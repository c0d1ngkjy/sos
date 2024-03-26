package com.study.sos_backend.business.repository.impl;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.NumberTemplate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.study.sos_backend.business.dto.BusinessInfoResponseDto;
import com.study.sos_backend.business.entity.QBusiness;
import com.study.sos_backend.business.repository.BusinessRepositoryCustom;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
public class BusinessRepositoryCustomImpl implements BusinessRepositoryCustom {

    private static final Double MAX_DISTANCE = 5000.0; // 5KM
    private final JPAQueryFactory queryFactory;

    public BusinessRepositoryCustomImpl(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public Page<BusinessInfoResponseDto> getAllBusiness(Pageable pageable) {
        QBusiness business = QBusiness.business;

        List<BusinessInfoResponseDto> results = queryFactory.select(Projections.bean(BusinessInfoResponseDto.class, business)).offset(pageable.getOffset()).limit(pageable.getPageSize()).fetch();

        long size = Objects.requireNonNull(queryFactory.select(business.count()).from(business).fetchOne());

        return new PageImpl<>(results, pageable, size);
    }

    // TODO 가격순 구현도 해야함.
    // 값을 빼내서 프리미엄 업체 3곳 나열
    // 거리순, 가격순 17곳 나열 총 20개

    @Override
    public List<BusinessInfoResponseDto> findNearByBusinesses(double latitude, double longitude) {
        QBusiness business = QBusiness.business;

        // 거리순으로 동작됨

        NumberTemplate<Double> distanceExpression = Expressions.numberTemplate(Double.class,
                "ST_DISTANCE_SPHERE(point({0}, {1}), point({2}, {3}))",
                business.locate.latitude,
                business.locate.longitude,
                latitude,
                longitude);

        return queryFactory
                .select(Projections
                        .constructor(BusinessInfoResponseDto.class, business, distanceExpression))
                .from(business)
                .where(distanceExpression.loe(MAX_DISTANCE))
                .orderBy(distanceExpression.asc())
                // 20개 제한
                .limit(20)
                .fetch();
    }
}
