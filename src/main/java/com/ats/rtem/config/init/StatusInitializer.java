package com.ats.rtem.config.init;

import com.ats.rtem.domain.dao.StatusDao;
import com.ats.rtem.domain.entity.Status;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class StatusInitializer {

    private final StatusDao statusDao;

    public StatusInitializer(StatusDao statusDao) {
        this.statusDao = statusDao;
    }

    @PostConstruct
    @Transactional
    public void initializeStatuses(){
        createStatusIfNotExist("Active");
        createStatusIfNotExist("Inactive");
        createStatusIfNotExist("Suspended");
        createStatusIfNotExist("Terminated");
        createStatusIfNotExist("On-leave");
        createStatusIfNotExist("Approved");
        createStatusIfNotExist("Pending");
        createStatusIfNotExist("Rejected");
    }

    private void createStatusIfNotExist(String statusName){
        if(statusDao.findByStatusName(statusName) == null){
            Status status = new Status();
            status.setStatusName(statusName);
            statusDao.save(status);
            log.info("Status '{}' created", statusName);
        }else{
            log.info("Status '{}' already exist", statusName);
        }
    }
}
