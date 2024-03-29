package com.study.sos_backend.reservation.repository;

import com.study.sos_backend.reservation.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReserveRepository extends JpaRepository<Reservation, Long> {
}
