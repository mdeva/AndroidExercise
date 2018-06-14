package com.example.deverajan.androidexcercise;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class UITest {

    @Rule
    public ActivityTestRule<ListActivity> mActivityRule =
            new ActivityTestRule<>(ListActivity.class);
    @Test
    public void recyclerviewTest() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withId(R.id.list)).check(ViewAssertions.matches(isDisplayed()));

        onView(withId(R.id.list)).perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));

        onView(withId(R.id.list)).perform(RecyclerViewActions.scrollToPosition(1));

    }
}
