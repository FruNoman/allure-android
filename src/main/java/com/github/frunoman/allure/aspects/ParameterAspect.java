package com.github.frunoman.allure.aspects;

import com.github.frunoman.allure.Allure;
import com.github.frunoman.allure.AllureLifecycle;
import com.github.frunoman.allure.util.AspectUtils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.util.Arrays;

import java8.util.Objects;


@Aspect
public class ParameterAspect {
    private static AllureLifecycle lifecycle;

    public static AllureLifecycle getLifecycle() {
        if (Objects.isNull(lifecycle)) {
            lifecycle = Allure.getLifecycle();
        }
        return lifecycle;
    }

    public static void setLifecycle(final AllureLifecycle lifecycle) {
        ParameterAspect.lifecycle = lifecycle;
    }

    @Pointcut("@annotation(org.junit.Test)")
    public void testAnnotation() {
    }

    @SuppressWarnings("PMD.UnnecessaryLocalBeforeReturn")
    @Around("testAnnotation()")
    public Object parameterAspect(final ProceedingJoinPoint joinPoint) throws Throwable {
        final MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        final String uuid = getLifecycle().getCurrentTestCase().get();
        try {
            getLifecycle().updateParameters(uuid,AspectUtils.getParameters(methodSignature, joinPoint.getArgs()));
        }catch (Exception e){

        }
        final Object proceed = joinPoint.proceed();
        return proceed;
    }
}
