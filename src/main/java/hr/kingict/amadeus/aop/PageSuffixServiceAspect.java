package hr.kingict.amadeus.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PageSuffixServiceAspect {

    Logger logger = LoggerFactory.getLogger(DashboardServiceAspect.class);

    @Before("execution(* hr.kingict.amadeus.service.PageSuffixService.getSuffix(..))")
    public void beforePageSuffixServiceGetSuffix(JoinPoint joinPoint) {

        logger.warn("method beforePageSuffixServiceGetSuffix: " + joinPoint.getTarget().getClass().getSimpleName());
    }

    @AfterReturning(value = "execution(* hr.kingict.amadeus.service.PageSuffixService.getSuffix(..))",
            returning = "result")
    public void afterPageSuffixServiceGetSuffix(String result) {

        logger.warn("result of afterPageSuffixServiceGetSuffix: " + result);
    }
}
