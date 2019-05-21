package com.github.frunoman;


import com.github.frunoman.allure.Allure;
import com.github.frunoman.allure.AllureLifecycle;
import com.github.frunoman.allure.Epic;
import com.github.frunoman.allure.Feature;
import com.github.frunoman.allure.Owner;
import com.github.frunoman.allure.Severity;
import com.github.frunoman.allure.Story;
import com.github.frunoman.allure.util.ResultsUtils;
import com.github.frunoman.junit4.DisplayName;
import com.github.frunoman.junit4.Tag;
import com.github.frunoman.model_pojo.Label;
import com.github.frunoman.model_pojo.Status;
import com.github.frunoman.model_pojo.StatusDetails;
import com.github.frunoman.model_pojo.TestResult;


import java8.util.stream.RefStreams;
import java8.util.stream.StreamSupport;
import org.junit.Ignore;
import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;

import java.lang.annotation.Annotation;
import java.lang.annotation.Repeatable;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import java8.util.Objects;
import java8.util.Optional;

import java.util.UUID;

import java8.util.function.Function;
import java8.util.stream.Collectors;
import java8.util.stream.Stream;

import static com.github.frunoman.allure.util.ResultsUtils.getHostName;
import static com.github.frunoman.allure.util.ResultsUtils.getStatus;
import static com.github.frunoman.allure.util.ResultsUtils.getStatusDetails;
import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * Allure Junit4 listener.
 */
@RunListener.ThreadSafe
@SuppressWarnings({"PMD.ExcessiveImports", "PMD.CouplingBetweenObjects", "checkstyle:ClassFanOutComplexity"})
public class AndroidAllureListener extends RunListener {

    public static final String MD_5 = "md5";

    private final ThreadLocal<String> testCases = new InheritableThreadLocal<String>() {
        @Override
        protected String initialValue() {
            return UUID.randomUUID().toString();
        }
    };

    private final AllureLifecycle lifecycle;

    public AndroidAllureListener() {
        this(Allure.getLifecycle());
    }

    public AndroidAllureListener(final AllureLifecycle lifecycle) {
        this.lifecycle = lifecycle;
    }

    public AllureLifecycle getLifecycle() {
        return lifecycle;
    }

    @Override
    public void testRunStarted(final Description description) throws Exception {
        //do nothing
    }

    @Override
    public void testRunFinished(final Result result) throws Exception {
        //do nothing
    }

    @Override
    public void testStarted(final Description description) throws Exception {
        final String uuid = testCases.get();
        final TestResult result = createTestResult(uuid, description);
        getLifecycle().scheduleTestCase(result);
        getLifecycle().startTestCase(uuid);
    }

    @Override
    public void testFinished(final Description description) throws Exception {
        final String uuid = testCases.get();
        testCases.remove();
        getLifecycle().updateTestCase(uuid, testResult -> {
            if (Objects.isNull(testResult.getStatus())) {
                testResult.setStatus(Status.PASSED);
            }
        });

        getLifecycle().stopTestCase(uuid);
        getLifecycle().writeTestCase(uuid);
    }

    @Override
    public void testFailure(final Failure failure) throws Exception {
        final String uuid = testCases.get();
        getLifecycle().updateTestCase(uuid, testResult -> testResult
                .withStatus(getStatus(failure.getException()).orElse(null))
                .withStatusDetails(getStatusDetails(failure.getException()).orElse(null))
        );
    }

    @Override
    public void testAssumptionFailure(final Failure failure) {
        final String uuid = testCases.get();
        getLifecycle().updateTestCase(uuid, testResult ->
                testResult.withStatus(Status.SKIPPED)
                        .withStatusDetails(getStatusDetails(failure.getException()).orElse(null))
        );
    }

    @Override
    public void testIgnored(final Description description) throws Exception {
        final String uuid = testCases.get();
        testCases.remove();

        final TestResult result = createTestResult(uuid, description);
        result.setStatus(Status.SKIPPED);
        result.setStatusDetails(getIgnoredMessage(description));
        result.setStart(System.currentTimeMillis());

        getLifecycle().scheduleTestCase(result);
        getLifecycle().stopTestCase(uuid);
        getLifecycle().writeTestCase(uuid);
    }

    private Optional<String> getDisplayName(final Description result) {
        return Optional.ofNullable(result.getAnnotation(DisplayName.class))
                .map(DisplayName::value);
    }

    private Optional<String> getDescription(final Description result) {
        return Optional.ofNullable(result.getAnnotation(com.github.frunoman.allure.Description.class))
                .map(com.github.frunoman.allure.Description::value);
    }

    private List<com.github.frunoman.model_pojo.Link> getLinks(final Description result) {
        Stream<com.github.frunoman.model_pojo.Link> stream = RefStreams.empty();
        return stream.collect(Collectors.toList());
//        return RefStreams.of(
////                StreamSupport.stream(getAnnotationsOnClass(result, com.github.frunoman.allure.Link.class)).map(ResultsUtils::createLink),
////                StreamSupport.stream(getAnnotationsOnMethod(result, com.github.frunoman.allure.Link.class)).map(ResultsUtils::createLink),
////                StreamSupport.stream(getAnnotationsOnClass(result, com.github.frunoman.allure.Issue.class)).map(ResultsUtils::createLink),
////                StreamSupport.stream(getAnnotationsOnMethod(result, com.github.frunoman.allure.Issue.class)).map(ResultsUtils::createLink),
////                StreamSupport.stream(getAnnotationsOnClass(result, com.github.frunoman.allure.TmsLink.class)).map(ResultsUtils::createLink),
////                StreamSupport.stream(getAnnotationsOnMethod(result, com.github.frunoman.allure.TmsLink.class)).map(ResultsUtils::createLink)
//        ).reduce(RefStreams::concat).orElseGet(RefStreams::empty).collect(Collectors.toList());
    }

    private List<Label> getLabels(final Description result) {
        Stream<Label> stream = RefStreams.empty();
        return stream.collect(Collectors.toList());
//        return RefStreams.of(
//                getLabels(result, Epic.class, ResultsUtils::createLabel),
//                getLabels(result, Feature.class, ResultsUtils::createLabel),
//                getLabels(result, Story.class, ResultsUtils::createLabel),
//                getLabels(result, Severity.class, ResultsUtils::createLabel),
//                getLabels(result, Owner.class, ResultsUtils::createLabel),
//                getLabels(result, Tag.class, this::createLabel)
//        ).reduce(RefStreams::concat).orElseGet(RefStreams::empty).collect(Collectors.toList());
    }

    private <T extends Annotation> Stream<Label> getLabels(final Description result, final Class<T> labelAnnotation,
                                                           final Function<T, Label> extractor) {

        final List<Label> labels = StreamSupport.stream(getAnnotationsOnMethod(result, labelAnnotation))
                .map(extractor)
                .collect(Collectors.toList());

//        if (labelAnnotation.isAnnotationPresent(Repeatable.class) || labels.isEmpty()) {
//            final Stream<Label> onClassLabels =  StreamSupport.stream(getAnnotationsOnClass(result, labelAnnotation))
//                    .map(extractor);
//            labels.addAll(onClassLabels.collect(Collectors.toList()));
//        }

        return StreamSupport.stream(labels);
    }

    private Label createLabel(final Tag tag) {
        return new Label().withName("tag").withValue(tag.value());
    }

    private <T extends Annotation> List<T> getAnnotationsOnMethod(final Description result, final Class<T> clazz) {
        final T annotation = result.getAnnotation(clazz);
        Stream<T> stream = RefStreams.empty();
        if (Objects.nonNull(annotation)) {
            stream = RefStreams.of(annotation);
        }
        return stream.collect(Collectors.toList());
    }

    @SuppressWarnings("unchecked")
    private <T extends Annotation> List<T> extractRepeatable(final Description result, final Class<T> clazz) {
        return Collections.emptyList();
    }

//    private <T extends Annotation> List<T> getAnnotationsOnClass(final Description result, final Class<T> clazz) {
//        return RefStreams.of(result)
//                .map(Description::getTestClass)
//                .filter(Objects::nonNull)
//                .map(testClass -> testClass.getAnnotations(clazz))
//                .flatMap(RefStreams::of)
//                .collect(Collectors.toList());
//    }

    private String getHistoryId(final Description description) {
        return md5(description.getClassName() + description.getMethodName());
    }

    private String md5(final String source) {
        final byte[] bytes = getMessageDigest().digest(source.getBytes(UTF_8));
        return new BigInteger(1, bytes).toString(16);
    }

    private MessageDigest getMessageDigest() {
        try {
            return MessageDigest.getInstance(MD_5);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("Could not find md5 hashing algorithm", e);
        }
    }

    private String getPackage(final Class<?> testClass) {
        return Optional.ofNullable(testClass)
                .map(Class::getPackage)
                .map(Package::getName)
                .orElse("");
    }

    private StatusDetails getIgnoredMessage(final Description description) {
        final Ignore ignore = description.getAnnotation(Ignore.class);
        final String message = Objects.nonNull(ignore) && !ignore.value().isEmpty()
                ? ignore.value() : "Test ignored (without reason)!";
        return new StatusDetails().withMessage(message);
    }

    private TestResult createTestResult(final String uuid, final Description description) {
        final String className = description.getClassName();
        final String methodName = description.getMethodName();
        final String name = Objects.nonNull(methodName) ? methodName : className;
        final String fullName = Objects.nonNull(methodName) ? String.format("%s.%s", className, methodName) : className;
        final String suite = Optional.ofNullable(description.getTestClass())
                .map(it -> it.getAnnotation(DisplayName.class))
                .map(DisplayName::value).orElse(className);

        final TestResult testResult = new TestResult()
                .withUuid(uuid)
                .withHistoryId(getHistoryId(description))
                .withName(name)
                .withFullName(fullName)
                .withLinks(getLinks(description))
                .withLabels(
                        new Label().withName("package").withValue(getPackage(description.getTestClass())),
                        new Label().withName("testClass").withValue(className),
                        new Label().withName("testMethod").withValue(name),
                        new Label().withName("suite").withValue(suite),
                        new Label().withName("host").withValue(getHostName()),
                        new Label().withName("thread").withValue(Thread.currentThread().getName() + Thread.currentThread().getId())
                );
//        testResult.getLabels().addAll(getLabels(description));
        getDisplayName(description).ifPresent(testResult::setName);
        getDescription(description).ifPresent(testResult::setDescription);
        return testResult;
    }

}

