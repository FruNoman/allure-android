package com.github.frunoman.allure.util;


import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Properties;

/**
 * The collection of properties utils methods.
 */
public final class PropertiesUtils {

    private static String instrumentationRegistry = "android.support.test.InstrumentationRegistry";
    private static String getContextMethod = "getContext";
    private static String getAssetsMethod = "getAssets";
    private static String openMethod = "open";

    private static final String ALLURE_PROPERTIES_FILE = "allure.properties";

    private PropertiesUtils() {
    }

    public static Properties loadAllureProperties() {
        Properties prop = new Properties();
        try {
//            Class<?> instrumentationRegistryClazz = Class.forName(instrumentationRegistry);
//            Method getContext = instrumentationRegistryClazz.getMethod(getContextMethod);
//            Object context = getContext.invoke((Object) null);
//            Method getAssets = context.getClass().getMethod(getAssetsMethod);
//            Object assets = getAssets.invoke(context);
//            Method open = assets.getClass().getMethod(openMethod, String.class);
//            Object assetProps = open.invoke(assets, "allure.properties");
//            InputStream stream = (InputStream) assetProps;
//            prop.load(stream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return prop;
    }

}
