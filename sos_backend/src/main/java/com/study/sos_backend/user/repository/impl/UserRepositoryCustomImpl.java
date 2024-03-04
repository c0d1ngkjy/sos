package com.study.sos_backend.user.repository.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.study.sos_backend.user.repository.UserRepositoryCustom;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryCustomImpl implements UserRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public UserRepositoryCustomImpl(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

}
