package com.github.frunoman.allure.util;

import com.github.frunoman.model_pojo.Parameter;

import org.aspectj.lang.reflect.MethodSignature;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java8.util.stream.IntStreams;

import java8.util.Objects;



/**
 * @author charlie (Dmitry Baev).
 */
public final class AspectUtils {

    private AspectUtils() {
        throw new IllegalStateException("Do not instance");
    }

    public static Parameter[] getParameters(final MethodSignature signature, final Object... args) {
        return IntStreams.range(0, args.length).mapToObj(index -> {
            final String name = signature.getParameterNames()[index];
            final String value = objectToString(args[index]);
            return new Parameter().withName(name).withValue(value);
        }).toArray(Parameter[]::new);
    }

    public static Map<String, Object> getParametersMap(final MethodSignature signature, final Object... args) {
        final String[] parameterNames = signature.getParameterNames();
        final Map<String, Object> params = new HashMap<>();
        params.put("method", signature.getName());
        for (int i = 0; i < Math.max(parameterNames.length, args.length); i++) {
            params.put(parameterNames[i], args[i]);
            params.put(Integer.toString(i), args[i]);
        }
        return params;
    }

    public static String objectToString(final Object object) {
        if (Objects.nonNull(object) && (object instanceof Object[])) {
            return Arrays.toString((Object[]) object);
        }
        return Objects.toString(object);
    }
}
