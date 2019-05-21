package com.github.frunoman.allure.aspects;


import com.github.frunoman.allure.Allure;
import com.github.frunoman.allure.AllureLifecycle;
import com.github.frunoman.allure.Step;
import com.github.frunoman.model_pojo.Parameter;
import com.github.frunoman.model_pojo.Status;
import com.github.frunoman.model_pojo.StepResult;



import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;

import java8.util.Objects;
import java8.util.Optional;
import java.util.UUID;

import static com.github.frunoman.allure.util.AspectUtils.getParameters;
import static com.github.frunoman.allure.util.AspectUtils.getParametersMap;
import static com.github.frunoman.allure.util.NamingUtils.processNameTemplate;
import static com.github.frunoman.allure.util.ResultsUtils.getStatusDetails;
import static com.github.frunoman.allure.util.ResultsUtils.getStatusNot;


/**
 * @author Dmitry Baev charlie@yandex-team.ru
 * Date: 24.10.13
 * @author sskorol (Sergey Korol)
 */
@Aspect
public class StepsAspects {


    private static AllureLifecycle lifecycle;


    @SuppressWarnings("PMD.UnnecessaryLocalBeforeReturn")
    @Around("@annotation(com.github.frunoman.allure.Step) && execution(* *(..))")
    public Object step(final ProceedingJoinPoint joinPoint) throws Throwable {
        final MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        final Step step = methodSignature.getMethod().getAnnotation(Step.class);
        final String uuid = UUID.randomUUID().toString();
        String returnState = "void";
        final String name = Optional.of(step.value())
                .filter(StringUtils::isNoneEmpty)
                .map(value -> processNameTemplate(value, getParametersMap(methodSignature, joinPoint.getArgs())))
                .orElse(methodSignature.getName());

        final StepResult result = new StepResult()
                .withName(name)
                .withParameters(getParameters(methodSignature, joinPoint.getArgs()));
        getLifecycle().startStep(uuid, result);
        try {
            final Object proceed = joinPoint.proceed();
            if(proceed!=null){
                returnState = proceed.toString();
            }
            getLifecycle().updateStep1(uuid, result.withStatus(Status.PASSED));
            return proceed;
        } catch (Throwable e) {
            getLifecycle().updateStep1(uuid, result.withStatus(getStatusNot(e))
                    .withStatusDetails(getStatusDetails(e).orElse(null)));
            throw e;
        } finally {
            getLifecycle().updateStep1(uuid, result.withParameters(new Parameter().withName("return")
                    .withValue(returnState)));
            getLifecycle().stopStep(uuid);
        }
    }

    /**
     * For tests only.
     *
     * @param allure allure lifecycle to set.
     */
    public static void setLifecycle(final AllureLifecycle allure) {
        lifecycle = allure;
    }

    public static AllureLifecycle getLifecycle() {
        if (Objects.isNull(lifecycle)) {
            lifecycle = Allure.getLifecycle();
        }
        return lifecycle;
    }
}
