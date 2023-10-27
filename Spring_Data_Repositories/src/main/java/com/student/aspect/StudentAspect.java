package com.student.aspect;

import com.student.core.Student;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import javax.inject.Named;

@Aspect
@Named
public class StudentAspect {
    private Logger LOG = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @Pointcut("execution(* com.student.service.*.*(..))")
    public void log(){
    }

    @Before("log()")
    public void logIn(JoinPoint jp){
        LOG.info("Started Method    : " +  jp.getSignature().getName());
    }

    @After("log()")
    public void logOut(JoinPoint jp){

        LOG.info("Method Successful : "  + jp.getSignature().getName());
    }
    @Around("log() && args(id)")
    public Object around(ProceedingJoinPoint jp, long id) throws Throwable {
        LOG.info("Around before -> " + jp.getSignature().getName() + " with id " + id);
        Student student = (Student) jp.proceed();
        LOG.info("Around after  -> " + student.getFirstName() + " " + student.getSurname());
        return student;
    }

}
