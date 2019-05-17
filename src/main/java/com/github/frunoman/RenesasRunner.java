package com.github.frunoman;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.manipulation.Filter;
import org.junit.runner.manipulation.NoTestsRemainException;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.TestClass;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class RenesasRunner extends BlockJUnit4ClassRunner {

    List<FrameworkMethod> computedTestMethods;
    Map<FrameworkMethod, List<FrameworkMethod>> dataProviderMethods;

    public RenesasRunner(Class<?> klass) throws InitializationError {
        super(klass);
    }

    @Override public void run(RunNotifier notifier){
        notifier.addListener(new RenesasListener());
        notifier.fireTestRunStarted(getDescription());
        super.run(notifier);
    }

}
