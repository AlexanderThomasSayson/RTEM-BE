package com.ats.rtem.domain.dao;

import com.ats.rtem.domain.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusDao extends JpaRepository<Status, Long> {

    Status findByStatusName(String statusName);
}
