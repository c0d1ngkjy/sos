package com.study.sos_backend.membership.repository;

import com.study.sos_backend.business.entity.Business;
import com.study.sos_backend.membership.entity.Membership;
import com.study.sos_backend.membership.enums.MembershipType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface MemberShipRepository extends JpaRepository<Membership, Long> {

    Optional<Membership> findByBusiness(Business business);

    Optional<Membership> findByBusinessId(Long businessId);

    List<Membership> findAllByEndDateTimeBeforeAndType(LocalDateTime dateTime, MembershipType type);
}
