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
public class DashboardServiceAspect {

    Logger logger = LoggerFactory.getLogger(DashboardServiceAspect.class);

    @Before("execution(* hr.kingict.amadeus.service.DashboardService.getDashboard(..))")
    public void beforeDashboardServiceGetDashboard(JoinPoint joinPoint) {

        logger.warn("method beforeDashboardServiceGetDashboard: " + joinPoint.getTarget().getClass().getSimpleName());
    }

    @AfterReturning(value = "execution(* hr.kingict.amadeus.service.DashboardService.getDashboard(..))",
    returning = "result")
    public void afterDashboardServiceGetDashboard(String result) {

        logger.warn("result of afterDashboardServiceGetDashboard: " + result);
    }
}
