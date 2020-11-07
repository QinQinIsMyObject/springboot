package com.zpark.circle_shop.aspect;

import com.zpark.circle_shop.service.UserRecordService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Celery
 */
@Component
@Aspect
public class UserRegisterRecordAspect {

    @Autowired
    private UserRecordService userRecordService;

    @Pointcut("@annotation(com.zpark.circle_shop.annotation.UserRegisterRecord)")
    public void userRegisterRecord() {
    }

    /**
     * 对用户注册进行的记录，返回后增强
     *
     * @param joinPoint 加入点（可忽略）
     * @return
     */
    @AfterReturning(value = "userRegisterRecord()", returning = "result")
    public Object record(JoinPoint joinPoint, Object result) {
        //记录业务
        Integer row = (Integer) result;
        userRecordService.recordRegister(row);
        return result;
    }

}
