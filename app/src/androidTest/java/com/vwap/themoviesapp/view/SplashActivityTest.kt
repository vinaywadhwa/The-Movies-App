package com.vwap.themoviesapp.view

import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.IdlingResource
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.vwap.themoviesapp.R
import junit.framework.Assert.assertTrue
import org.junit.Rule
import org.junit.Test


class SplashActivityTest {
    @get: Rule
    val activityRule = ActivityScenarioRule(SplashActivity::class.java)

    @Test
    fun test_isActivityInView() {
        onView(withId(R.id.splash_activity_layout_root))
            .check(matches(isDisplayed()))
    }

    @Test
    fun test_isSplashTimerWorking() {
        //setup a timer, that waits for the given time
        val idlingResource: IdlingResource = ElapsedTimeIdlingResource(SPLASH_TIMER + 100)
        IdlingRegistry.getInstance().register(idlingResource)

        onView(withId(R.id.activity_layout_root))
            .check(matches(isDisplayed()))

        //unregister the timer resource
        IdlingRegistry.getInstance().unregister(idlingResource)

    }

    /**
     * Checks if splash is finished after launching main activity.
     */
    @Test
    fun test_isSplashProperlyDisposed() {
        //setup a timer, that waits for the given time
        val idlingResource: IdlingResource = ElapsedTimeIdlingResource(SPLASH_TIMER + 100)
        IdlingRegistry.getInstance().register(idlingResource)

        //check if main activity is opened and splash is destroyed (not paused/stopped)
        onView(withId(R.id.activity_layout_root))
            .check(matches(isDisplayed()))
        assertTrue(activityRule.scenario.state == Lifecycle.State.DESTROYED)

        //unregister the timer resource
        IdlingRegistry.getInstance().unregister(idlingResource)

    }
}