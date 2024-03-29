package com.study.sos_backend.business.repository;

import com.study.sos_backend.business.entity.Business;
import com.study.sos_backend.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BusinessRepository extends JpaRepository<Business, Long>, BusinessRepositoryCustom {
    void deleteAllByOwner(User user);

}
