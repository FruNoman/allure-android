package com.github.frunoman;


import org.junit.runner.notification.RunNotifier;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;

import java.util.List;
import java.util.Map;


public class AndroidAllureJunit4Runner extends BlockJUnit4ClassRunner {

    List<FrameworkMethod> computedTestMethods;
    Map<FrameworkMethod, List<FrameworkMethod>> dataProviderMethods;

    public AndroidAllureJunit4Runner(Class<?> klass) throws InitializationError {
        super(klass);
    }

    @Override public void run(RunNotifier notifier){
        notifier.addListener(new AndroidAllureListener());
        notifier.fireTestRunStarted(getDescription());
        super.run(notifier);
    }

}
