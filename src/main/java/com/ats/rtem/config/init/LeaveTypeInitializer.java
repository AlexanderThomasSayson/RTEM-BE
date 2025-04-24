package com.ats.rtem.config.init;

import com.ats.rtem.domain.dao.LeaveTypeDao;
import com.ats.rtem.domain.entity.LeaveType;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class LeaveTypeInitializer {

    private final LeaveTypeDao leaveTypeDao;

    public LeaveTypeInitializer(LeaveTypeDao leaveTypeDao) {
        this.leaveTypeDao = leaveTypeDao;
    }

    @PostConstruct
    @Transactional
    public void initializeLeaveType(){
        createLeaveTypeIfNotExist("Vacation");
        createLeaveTypeIfNotExist("Sick");
        createLeaveTypeIfNotExist("Maternity");
        createLeaveTypeIfNotExist("Paternity");
    }

    private void createLeaveTypeIfNotExist(String leaveClassification){
        if(leaveTypeDao.findByLeaveClassification(leaveClassification) == null){
            LeaveType leaveType = new LeaveType();
            leaveType.setLeaveClassification(leaveClassification);
            leaveTypeDao.save(leaveType);
            log.info("Leave type '{}' created", leaveClassification);
        }else{
            log.info("Leave type '{}' already exist", leaveClassification);
        }
    }
}
