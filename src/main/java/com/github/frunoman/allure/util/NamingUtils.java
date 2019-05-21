package com.github.frunoman.allure.util;



import java.util.Arrays;
import java.util.Map;
import java8.util.Objects;
import java8.util.Optional;
import java8.util.Spliterator;
import java8.util.stream.*;

import static org.joor.Reflect.on;

/**
 * @author charlie (Dmitry Baev).
 */
public final class NamingUtils {



    private static final Collector<CharSequence, ?, String> JOINER = Collectors.joining(", ", "[", "]");

    private NamingUtils() {
        throw new IllegalStateException("Do not instance");
    }

    public static String processNameTemplate(final String template, final Map<String, Object> params) {
//        final Matcher matcher = Pattern.compile("\\{([^}]*)}").matcher(template);
//        final StringBuffer sb = new StringBuffer();
//        while (matcher.find()) {
//            final String pattern = matcher.group(1);
//            final String replacement = processPattern(pattern, params).orElseGet(matcher::group);
//            matcher.appendReplacement(sb, Matcher.quoteReplacement(replacement));
//        }
//        matcher.appendTail(sb);
//        return sb.toString();
        return template;
    }

    @SuppressWarnings("ReturnCount")
    private static Optional<String> processPattern(final String pattern, final Map<String, Object> params) {
        if (pattern.isEmpty()) {

            return Optional.empty();
        }
        final String[] parts = pattern.split("\\.");
        final String parameterName = parts[0];
        if (!params.containsKey(parameterName)) {

            return Optional.empty();
        }
        final Object param = params.get(parameterName);
        return Optional.ofNullable(extractProperties(param, parts, 1));
    }

    @SuppressWarnings("ReturnCount")
    private static String extractProperties(final Object object, final String[] parts, final int index) {
        if (Objects.isNull(object)) {
            return "null";
        }
        if (index < parts.length) {
            if (object instanceof Object[]) {
                return RefStreams.of((Object[]) object)
                        .map(child -> extractProperties(child, parts, index))
                        .collect(JOINER);
            }
            if (object instanceof Iterable) {
                final Spliterator<?> iterator = (Spliterator<?>) ((Iterable) object).spliterator();
                return StreamSupport.stream(iterator, false)
                        .map(child -> extractProperties(child, parts, index))
                        .collect(JOINER);
            }
            final Object child = on(object).get(parts[index]);
            return extractProperties(child, parts, index + 1);
//            return null;
        }
        if (object instanceof Object[]) {
            return Arrays.toString((Object[]) object);
        }
        return String.valueOf(object);
    }
}
