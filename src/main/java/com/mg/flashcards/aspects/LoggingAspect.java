package com.mg.flashcards.aspects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StopWatch;

@Aspect
@Configuration
public class LoggingAspect {

    @Pointcut("execution(* com.mg.flashcards.services.*.*(..))")
    public void servicesPointCut (){}

    @Pointcut("execution(* com.mg.flashcards.rest.controllers.*.*(..))")
    public void restControllersPointCut (){}

    @Around("servicesPointCut() || restControllersPointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {

        final Logger logger = LogManager.getLogger(joinPoint.getTarget().getClass());

        try {

            StringBuffer startMessage = new StringBuffer();
            startMessage.append("Start method ");
            startMessage.append(joinPoint.getSignature().getName()).append("(");
            Object[] args = joinPoint.getArgs();
            for(int i = 0; i < args.length; i++){
                startMessage.append(args[i]);
                if(i < args.length - 1){
                    startMessage.append(", ");
                }
            }
            startMessage.append(")");

            logger.info(startMessage.toString());

            StopWatch stopWatch = new StopWatch();
            stopWatch.start();

            Object result = joinPoint.proceed();

            stopWatch.stop();

            StringBuffer endMessage = new StringBuffer();
            endMessage.append("Finish method ");
            endMessage.append(joinPoint.getSignature().getName());
            endMessage.append("(..); ");
            String returnTypeString = ((MethodSignature)joinPoint.getSignature()).getReturnType().toString();
            if(!returnTypeString.equals("void")) {
                endMessage.append("result ==> ");
                endMessage.append(result).append("; ");
            }
            endMessage.append("execution time: ");
            endMessage.append(stopWatch.getTotalTimeMillis()).append(" ms;");

            logger.info(endMessage.toString());

            return result;

        } catch (Throwable ex) {

            logger.error(ex);
            throw ex;
        }

    }
}
