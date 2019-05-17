package com.github.frunoman.allure.listener;


import com.github.frunoman.model_pojo.FixtureResult;

/**
 * Notifies about Allure test fixtures lifecycle events.
 *
 * @since 2.0
 */
public interface FixtureLifecycleListener {

    default void beforeFixtureStart(FixtureResult result) {
        //do nothing
    }

    default void afterFixtureStart(FixtureResult result) {
        //do nothing
    }

    default void beforeFixtureUpdate(FixtureResult result) {
        //do nothing
    }

    default void afterFixtureUpdate(FixtureResult result) {
        //do nothing
    }

    default void beforeFixtureStop(FixtureResult result) {
        //do nothing
    }

    default void afterFixtureStop(FixtureResult result) {
        //do nothing
    }

}
