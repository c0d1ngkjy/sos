package com.study.sos_backend.user.repository;

import com.study.sos_backend.user.entity.ProviderType;
import com.study.sos_backend.user.entity.RoleType;
import com.study.sos_backend.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom {

    Optional<User> findByEmail(String email);

    Optional<User> findByRefreshToken(String refreshToken);

    boolean existsAllByEmailAndRoleType(String email, RoleType roleType);

    Optional<User> findBySocialIdAndProviderType(String socialId, ProviderType providerType);

}
