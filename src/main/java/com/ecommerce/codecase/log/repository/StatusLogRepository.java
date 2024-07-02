package com.ecommerce.codecase.log.repository;

import com.ecommerce.codecase.log.entity.StatusLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StatusLogRepository extends JpaRepository<StatusLog, Long> {
    List<StatusLog> findAllByListingId(Long id);
}
