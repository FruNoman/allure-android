package com.github.frunoman.allure.listener;

import com.github.frunoman.model_pojo.FixtureResult;
import com.github.frunoman.model_pojo.StepResult;
import com.github.frunoman.model_pojo.TestResult;
import com.github.frunoman.model_pojo.TestResultContainer;

import java.util.List;


/**
 * @since 2.0
 */
@SuppressWarnings("PMD.TooManyMethods")
public class LifecycleNotifier implements ContainerLifecycleListener,
        TestLifecycleListener, FixtureLifecycleListener, StepLifecycleListener {

    private final List<ContainerLifecycleListener> containerListeners;

    private final List<TestLifecycleListener> testListeners;

    private final List<FixtureLifecycleListener> fixtureListeners;

    private final List<StepLifecycleListener> stepListeners;

    public LifecycleNotifier(final List<ContainerLifecycleListener> containerListeners,
                             final List<TestLifecycleListener> testListeners,
                             final List<FixtureLifecycleListener> fixtureListeners,
                             final List<StepLifecycleListener> stepListeners) {
        this.containerListeners = containerListeners;
        this.testListeners = testListeners;
        this.fixtureListeners = fixtureListeners;
        this.stepListeners = stepListeners;
    }


    @Override
    public void beforeTestSchedule(final TestResult result) {
        for(TestLifecycleListener listener:testListeners){
            listener.beforeTestSchedule(result);
        }
    }

    @Override
    public void afterTestSchedule(final TestResult result) {
        for(TestLifecycleListener listener:testListeners){
            listener.afterTestSchedule(result);
        }
    }

    @Override
    public void beforeTestUpdate(final TestResult result) {
        for(TestLifecycleListener listener:testListeners){
            listener.beforeTestUpdate(result);
        }
    }

    @Override
    public void afterTestUpdate(final TestResult result) {
        for(TestLifecycleListener listener:testListeners){
            listener.afterTestUpdate(result);
        }
    }

    @Override
    public void beforeTestStart(final TestResult result) {
        for(TestLifecycleListener listener:testListeners){
            listener.beforeTestStart(result);
        }
    }

    @Override
    public void afterTestStart(final TestResult result) {
        for(TestLifecycleListener listener:testListeners){
            listener.afterTestStart(result);
        }
    }

    @Override
    public void beforeTestStop(final TestResult result) {
        for(TestLifecycleListener listener:testListeners){
            listener.beforeTestStop(result);
        }
    }

    @Override
    public void afterTestStop(final TestResult result) {
        for(TestLifecycleListener listener:testListeners){
            listener.afterTestStop(result);
        }
    }

    @Override
    public void beforeTestWrite(final TestResult result) {
        for(TestLifecycleListener listener:testListeners){
            listener.beforeTestWrite(result);
        }
    }

    @Override
    public void afterTestWrite(final TestResult result) {
        for(TestLifecycleListener listener:testListeners){
            listener.afterTestWrite(result);
        }
    }

    @Override
    public void beforeContainerStart(final TestResultContainer container) {
        for(ContainerLifecycleListener listener:containerListeners){
            listener.beforeContainerStart(container);
        }
    }

    @Override
    public void afterContainerStart(final TestResultContainer container) {
        for(ContainerLifecycleListener listener:containerListeners){
            listener.afterContainerStart(container);
        }
    }

    @Override
    public void beforeContainerUpdate(final TestResultContainer container) {
        for(ContainerLifecycleListener listener:containerListeners){
            listener.beforeContainerUpdate(container);
        }
    }

    @Override
    public void afterContainerUpdate(final TestResultContainer container) {
        for(ContainerLifecycleListener listener:containerListeners){
            listener.afterContainerUpdate(container);
        }
    }

    @Override
    public void beforeContainerStop(final TestResultContainer container) {
        for(ContainerLifecycleListener listener:containerListeners){
            listener.beforeContainerStop(container);
        }
    }

    @Override
    public void afterContainerStop(final TestResultContainer container) {
        for(ContainerLifecycleListener listener:containerListeners){
            listener.afterContainerStop(container);
        }
    }

    @Override
    public void beforeContainerWrite(final TestResultContainer container) {
        for(ContainerLifecycleListener listener:containerListeners){
            listener.beforeContainerWrite(container);
        }
    }

    @Override
    public void afterContainerWrite(final TestResultContainer container) {
        for(ContainerLifecycleListener listener:containerListeners){
            listener.afterContainerWrite(container);
        }
    }

    @Override
    public void beforeFixtureStart(final FixtureResult result) {
        for(FixtureLifecycleListener listener:fixtureListeners){
            listener.beforeFixtureStart(result);
        }
    }

    @Override
    public void afterFixtureStart(final FixtureResult result) {
        for(FixtureLifecycleListener listener:fixtureListeners){
            listener.afterFixtureStart(result);
        }
    }

    @Override
    public void beforeFixtureUpdate(final FixtureResult result) {
        for(FixtureLifecycleListener listener:fixtureListeners){
            listener.beforeFixtureUpdate(result);
        }
    }

    @Override
    public void afterFixtureUpdate(final FixtureResult result) {
        for(FixtureLifecycleListener listener:fixtureListeners){
            listener.afterFixtureUpdate(result);
        }
    }

    @Override
    public void beforeFixtureStop(final FixtureResult result) {
        for(FixtureLifecycleListener listener:fixtureListeners){
            listener.beforeFixtureStop(result);
        }
    }

    @Override
    public void afterFixtureStop(final FixtureResult result) {
        for(FixtureLifecycleListener listener:fixtureListeners){
            listener.afterFixtureStop(result);
        }
    }

    @Override
    public void beforeStepStart(final StepResult result) {
        for(StepLifecycleListener listener:stepListeners){
            listener.beforeStepStart(result);
        }
    }

    @Override
    public void afterStepStart(final StepResult result) {
        for(StepLifecycleListener listener:stepListeners){
            listener.afterStepStart(result);
        }
    }

    @Override
    public void beforeStepUpdate(final StepResult result) {
        for(StepLifecycleListener listener:stepListeners){
            listener.beforeStepUpdate(result);
        }
    }

    @Override
    public void afterStepUpdate(final StepResult result) {
        for(StepLifecycleListener listener:stepListeners){
            listener.afterStepUpdate(result);
        }
    }

    @Override
    public void beforeStepStop(final StepResult result) {
        for(StepLifecycleListener listener:stepListeners){
            listener.beforeStepStop(result);
        }
    }

    @Override
    public void afterStepStop(final StepResult result) {
        for(StepLifecycleListener listener:stepListeners){
            listener.afterStepStop(result);
        }
    }
}
