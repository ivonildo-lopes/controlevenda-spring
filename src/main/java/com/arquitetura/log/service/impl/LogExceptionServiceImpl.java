package com.arquitetura.log.service.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.arquitetura.log.dao.LogExceptionDao;
import com.arquitetura.log.model.LogException;
import com.arquitetura.log.service.LogExceptionService;

@Service
public class LogExceptionServiceImpl implements LogExceptionService {

    @Autowired
    private LogExceptionDao logDao;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveLog(Throwable th) {
        LogException logExp = new LogException();
//        logExp.setNameUser(SecurityContextHolder.getContext().getAuthentication().getName());
        logExp.setNameUser("TESTE");
        logExp.setDtException(new Date());
        logExp.setException(getLog(th));
        try {
            logDao.save(logExp);
        }catch (Exception er){
            log.error(er.getMessage());
        }
    }


    private String getLog(Throwable ex){

        StringBuilder sb = new StringBuilder(ex.toString());
        for (StackTraceElement ste : ex.getStackTrace()) {
            sb.append("\n\tat ").append(ste);
        }


        while( (ex = ex.getCause()) != null ) {
            sb.append("\n");
            sb.append("\n\tat ").append(ex.getMessage());
            for (StackTraceElement ste : ex.getStackTrace()) {
                sb.append("\n\tat ").append(ste);
            }
        }

       return sb.toString();


    }
}
