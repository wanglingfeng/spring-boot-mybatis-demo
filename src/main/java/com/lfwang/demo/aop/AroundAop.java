package com.lfwang.demo.aop;

import com.lfwang.demo.controller.domain.MessageDTO;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * Created by lfwang on 2017/9/15.
 */
@Aspect
@Component
public class AroundAop {

    private static final String WORD = "wlf";
    
    @Around("execution(* com.lfwang.demo.service.MessageService.*(..))")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        
        for (Object arg : args) {
            if (arg instanceof MessageDTO) {
                MessageDTO dto = (MessageDTO) arg;

                // id为1时，进行劫持
                if (1 == dto.getId()) {
                    dto.setMessage("u are be kidnapped.");
                    
                    return "FUCK";
                }
            }
        }

        return joinPoint.proceed();
    }

    @Around("execution(* com.lfwang.demo.service.ExecuteService.*(..))")
    public Object executeAround(ProceedingJoinPoint joinPoint) throws Throwable {
        String word = (String) joinPoint.getArgs()[0];

        if (WORD.equals(word)) {
            return null;
        }

        return joinPoint.proceed();
    }
}
