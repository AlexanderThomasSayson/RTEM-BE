package com.ats.rtem.domain.dao;

import com.ats.rtem.domain.entity.LeaveType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeaveTypeDao extends JpaRepository<LeaveType, Long> {

    LeaveType findByLeaveClassification(String leaveClassification);
}
