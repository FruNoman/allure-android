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
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Pattern;

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

//    public String getCurrentTimeStamp() {
//        return new SimpleDateFormat("MM-dd HH:mm:ss.SSS").format(new Date());
//    }
//
//
//    public static String getLogs(String time) {
//        StringBuilder builder = new StringBuilder();
//        try {
//            String[] command = new String[]{"logcat", "-t", time};
//            Process process = Runtime.getRuntime().exec(command);
//            process.waitFor();
//            BufferedReader bufferedReader = new BufferedReader(
//                    new InputStreamReader(process.getInputStream()));
//
//            String line;
//            while ((line = bufferedReader.readLine()) != null) {
//                builder.append(line + "\n");
//            }
//        } catch (Exception ex) {
//        }
//        return builder.toString();
//    }
//
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
//        String time = getCurrentTimeStamp();
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

    @Pointcut("@annotation(com.github.frunoman.allure.Step) && execution(* *(..))")
    public void allureStep() {
    }

//    @Pointcut("call(* android.media..*.*(..))")
//    public void androidMediaAdapter() {
//    }

    @Pointcut("call(* android.bluetooth..*.*(..))")
    public void androidBluetoothAdapter() {
    }

    @Pointcut("call(* android.net.wifi..*.*(..))")
    public void androidWifiAdapter() {
    }

    @Pointcut("call(* android.hardware.usb..*.*(..))")
    public void androidUsbAdapter() {
    }

    @Pointcut("call(* android.support.test.uiautomator.UiDevice.*(..))")
    public void androidUiautomator() {
    }

    @Pointcut("call(* android.content.Context.startActivity(..))")
    public void androidContextStep() {
    }

    @Pointcut("call(* android.graphics..*.*(..))")
    public void androidGraphics() {
    }

    @Pointcut("call(* android.app..*.*(..))")
    public void androidApp() {
    }

    @Pointcut("call(* android.support..*.*(..))")
    public void androidSupport() {
    }

    @Pointcut("call(* android.widget..*.*(..))")
    public void androidImageView() {
    }

    @Pointcut("call(* com.github.renesasallure.MainActivity.*(..))")
    public void androidMainActivity() {
    }

//    @Pointcut("call(* org.junit.Assert.*(..))")
//    public void assertSupport() {
//    }

    @Pointcut("execution(* com.github.frunoman..*.*(..))")
    public void allureSupport() {
    }

    @Pointcut("execution(* com.github.renesasallure..*.*(..))")
    public void testsSupport() {
    }

//    @Pointcut("@annotation(org.junit.Before)")
//    public void beforeAnnotation() {
//    }
//
//    @Pointcut("@annotation(org.junit.After)")
//    public void afterAnnotation() {
//    }
//
//    @Pointcut("@annotation(org.junit.Test)")
//    public void testAnnotation() {
//    }

//    @Pointcut("call(* com.github.frunoman.asserts..*.*(..))")
//    public void softAssertSupport() {
//    }



    public Object stepMethod(final ProceedingJoinPoint joinPoint) throws Throwable {
    final MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        final String className = methodSignature.getDeclaringType().getSimpleName();
        final String uuid = UUID.randomUUID().toString();
        String returnState = "void";
        final String methodName =  methodSignature.getMethod().getName();
        Pattern p = Pattern.compile("(?=\\p{Lu})");
        String name = Arrays.asList(p.split(methodName)).toString()
                .replace(","," ")
                .replace("[","")
                .replace("]","")
                .toLowerCase();
        final StepResult result = new StepResult()
                .withName(className+" "+name)
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

    @SuppressWarnings("PMD.UnnecessaryLocalBeforeReturn")
    @Around("androidWifiAdapter()")
    public Object wifiAdapterStepMethod(final ProceedingJoinPoint joinPoint) throws Throwable {
       return stepMethod(joinPoint);
    }

    @SuppressWarnings("PMD.UnnecessaryLocalBeforeReturn")
    @Around("androidBluetoothAdapter()")
    public Object bluetoothAdapterStepMethod(final ProceedingJoinPoint joinPoint) throws Throwable {
        return stepMethod(joinPoint);
    }

    @SuppressWarnings("PMD.UnnecessaryLocalBeforeReturn")
    @Around("androidUsbAdapter()")
    public Object usbAdapterStepMethod(final ProceedingJoinPoint joinPoint) throws Throwable {
        return stepMethod(joinPoint);
    }

    @SuppressWarnings("PMD.UnnecessaryLocalBeforeReturn")
    @Around("androidUiautomator()")
    public Object androidUiautomatorStepMethod(final ProceedingJoinPoint joinPoint) throws Throwable {
        return stepMethod(joinPoint);
    }

    @SuppressWarnings("PMD.UnnecessaryLocalBeforeReturn")
    @Around("androidContextStep()")
    public Object androidContextStepMethod(final ProceedingJoinPoint joinPoint) throws Throwable {
        return stepMethod(joinPoint);
    }

    @SuppressWarnings("PMD.UnnecessaryLocalBeforeReturn")
    @Around("androidImageView()")
    public Object androidImageViewMethod(final ProceedingJoinPoint joinPoint) throws Throwable {
        return stepMethod(joinPoint);
    }

    @SuppressWarnings("PMD.UnnecessaryLocalBeforeReturn")
    @Around("androidMainActivity()")
    public Object androidandroidMainActivity(final ProceedingJoinPoint joinPoint) throws Throwable {
        return stepMethod(joinPoint);
    }

//    @SuppressWarnings("PMD.UnnecessaryLocalBeforeReturn")
//    @Around("androidMediaAdapter()")
//    public Object androidMediaAdapter(final ProceedingJoinPoint joinPoint) throws Throwable {
//        return stepMethod(joinPoint);
//    }

//    @SuppressWarnings("PMD.UnnecessaryLocalBeforeReturn")
//    @Around("softAssertSupport()")
//    public Object softAssertSupport(final ProceedingJoinPoint joinPoint) throws Throwable {
//        return stepMethod(joinPoint);
//    }

//    @SuppressWarnings("PMD.UnnecessaryLocalBeforeReturn")
//    @Around("assertSupport()")
//    public Object everyStep(final ProceedingJoinPoint joinPoint) throws Throwable {
//        final MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
//
//        final String uuid = UUID.randomUUID().toString();
//        final String className = "Check if ";
//        final String methodName =  methodSignature.getMethod().getName();
//        Pattern p = Pattern.compile("(?=\\p{Lu})");
//        String name = Arrays.asList(p.split(methodName)).toString()
//                .replace(","," ")
//                .replace("[","")
//                .replace("]","")
//                .toLowerCase();
//        final StepResult result = new StepResult()
//                .withName(className+" "+name)
//                .withParameters(getParameters(methodSignature, joinPoint.getArgs()));
//        getLifecycle().startStep(uuid, result);
//        try {
//            final Object proceed = joinPoint.proceed();
//            getLifecycle().updateStep1(uuid, result.withStatus(Status.PASSED));
//            return proceed;
//        } catch (Throwable e) {
//            getLifecycle().updateStep1(uuid, result.withStatus(getStatusNot(e))
//                    .withStatusDetails(getStatusDetails(e).orElse(null)));
//            DeviceUtils.takeScrenshot();
//            throw e;
//        } finally {
//            getLifecycle().stopStep(uuid);
//        }
//    }
//
//    @SuppressWarnings("PMD.UnnecessaryLocalBeforeReturn")
//    @Around("!allureSupport() && testsSupport() && !beforeAnnotation() && !afterAnnotation() && !allureStep() && !testAnnotation()")
//    public Object everyStepTest(final ProceedingJoinPoint joinPoint) throws Throwable {
//        final MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
//
//        final String uuid = UUID.randomUUID().toString();
//        final String className = methodSignature.getDeclaringType().getSimpleName();
//        final String methodName =  methodSignature.getMethod().getName();
//        Pattern p = Pattern.compile("(?=\\p{Lu})");
//        String name = Arrays.asList(p.split(methodName)).toString()
//                .replace(","," ")
//                .replace("[","")
//                .replace("]","")
//                .toLowerCase();
//        final StepResult result = new StepResult()
//                .withName(className+" "+ name)
//                .withParameters(getParameters(methodSignature, joinPoint.getArgs()));
//        String time = getCurrentTimeStamp();
////        System.out.println(name);
//        getLifecycle().startStep(uuid, result);
//        try {
//            final Object proceed = joinPoint.proceed();
//            getLifecycle().updateStep1(uuid, result.withStatus(Status.PASSED));
//            return proceed;
//        } catch (Throwable e) {
//            getLifecycle().updateStep1(uuid, result.withStatus(getStatusNot(e))
//                    .withStatusDetails(getStatusDetails(e).orElse(null)));
//            throw e;
//        } finally {
////            String logs = getLogs(time);
////            if (!logs.isEmpty()) {
////                getLifecycle().updateStep1(uuid, result.withParameters(new Parameter().withName("Logcat")
////                        .withValue(logs)));
////            }
//            getLifecycle().stopStep(uuid);
//        }
//    }

//    @SuppressWarnings("PMD.UnnecessaryLocalBeforeReturn")
//    @Around("beforeAnnotation()")
//    public Object beforeAnnotation(final ProceedingJoinPoint joinPoint) throws Throwable {
//        final MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
//
//        final String uuid = UUID.randomUUID().toString();
//        final String name = "Before test (" + methodSignature.getMethod().getName() + ")";
//
//        final StepResult result = new StepResult()
//                .withName(name)
//                .withParameters(getParameters(methodSignature, joinPoint.getArgs()));
//        getLifecycle().startStep(uuid, result);
//        try {
//            final Object proceed = joinPoint.proceed();
//            getLifecycle().updateStep1(uuid, result.withStatus(Status.PASSED));
//            return proceed;
//        } catch (Throwable e) {
//            getLifecycle().updateStep1(uuid, result.withStatus(getStatusNot(e))
//                    .withStatusDetails(getStatusDetails(e).orElse(null)));
//            throw e;
//        } finally {
//            getLifecycle().stopStep(uuid);
//        }
//    }
//
//    @SuppressWarnings("PMD.UnnecessaryLocalBeforeReturn")
//    @Around("afterAnnotation()")
//    public Object afterAnnotation(final ProceedingJoinPoint joinPoint) throws Throwable {
//        final MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
//
//        final String uuid = UUID.randomUUID().toString();
//        final String name = "After test (" + methodSignature.getMethod().getName() + ")";
//
//        final StepResult result = new StepResult()
//                .withName(name)
//                .withParameters(getParameters(methodSignature, joinPoint.getArgs()));
//        getLifecycle().startStep(uuid, result);
//        try {
//            final Object proceed = joinPoint.proceed();
//            getLifecycle().updateStep1(uuid, result.withStatus(Status.PASSED));
//            return proceed;
//        } catch (Throwable e) {
//            getLifecycle().updateStep1(uuid, result.withStatus(getStatusNot(e))
//                    .withStatusDetails(getStatusDetails(e).orElse(null)));
//            throw e;
//        } finally {
//            getLifecycle().stopStep(uuid);
//        }
//    }

//    @SuppressWarnings("PMD.UnnecessaryLocalBeforeReturn")
//    @Around("testAnnotation()")
//    public Object testAnnotations(final ProceedingJoinPoint joinPoint) throws Throwable {
//        final MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
//
//        final String uuid = UUID.randomUUID().toString();
//        final String name = "Test steps";
//
//        final StepResult result = new StepResult()
//                .withName(name)
//                .withParameters(getParameters(methodSignature, joinPoint.getArgs()));
//        getLifecycle().startStep(uuid, result);
//        try {
//            final Object proceed = joinPoint.proceed();
//            getLifecycle().updateStep1(uuid, result.withStatus(Status.PASSED));
//            return proceed;
//        } catch (Throwable e) {
//            getLifecycle().updateStep1(uuid, result.withStatus(getStatusNot(e))
//                    .withStatusDetails(getStatusDetails(e).orElse(null)));
//            throw e;
//        } finally {
//            getLifecycle().stopStep(uuid);
//        }
//    }


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
