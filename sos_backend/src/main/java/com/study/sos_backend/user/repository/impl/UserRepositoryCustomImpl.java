package com.study.sos_backend.user.repository.impl;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.study.sos_backend.business.entity.QBusiness;
import com.study.sos_backend.user.dto.UserInfoResponseDto;
import com.study.sos_backend.user.entity.QUser;
import com.study.sos_backend.user.entity.User;
import com.study.sos_backend.user.repository.UserRepositoryCustom;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class UserRepositoryCustomImpl implements UserRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public UserRepositoryCustomImpl(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public Page<UserInfoResponseDto> getUsers(Pageable pageable) {
        QUser user = QUser.user;

        List<UserInfoResponseDto> results = queryFactory
                .select(Projections.constructor(UserInfoResponseDto.class, user.id, user.email, user.roleType)).from(user)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long size = Objects.requireNonNull(queryFactory.select(user.count()).from(user).fetchOne());

        return new PageImpl<>(results, pageable, size);
    }

    @Override
    public Optional<UserInfoResponseDto> getBusinessOwner(Long id) {
        QUser user = QUser.user;
        QBusiness business = QBusiness.business;

        User result = queryFactory.selectFrom(user).where(business.id.eq(id)).join(user.businessList, business).fetchJoin().fetchOne();

        return Optional.of(new UserInfoResponseDto(Objects.requireNonNull(result)));
    }
}
