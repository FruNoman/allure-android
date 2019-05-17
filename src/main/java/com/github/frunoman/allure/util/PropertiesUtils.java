package com.github.frunoman.allure.util;



//import net.vrallev.android.context.AppContext;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

/**
 * The collection of properties utils methods.
 */
public final class PropertiesUtils {



    private static final String ALLURE_PROPERTIES_FILE = "allure.properties";

    private PropertiesUtils() {
    }

    public static Properties loadAllureProperties() {
        Properties prop = new Properties();
        try {
//            prop.load(AppContext.get().getAssets().open(ALLURE_PROPERTIES_FILE));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return prop;
    }

}
