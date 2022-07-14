package com.example.myapplication.view.people

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragment
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.util.UIState


import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class PeopleFragmentTest {
lateinit var scenario: FragmentScenario<PeopleFragment>
    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)
//
//    @Test
//    fun test_mainActivityIsDisplayed() {
//        emitLoadingState(UIState.Loading)
//        Espresso.onView(withId(R.id.main)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
//    }


//
//    @Before
//    fun setUp() {
//        scenario = launchFragmentInContainer ()
//        scenario.moveToState(Lifecycle.State.STARTED)
//    }

//    @Test
//    fun test_isFragmentArticleListVisible() {
//        emitLoadingState(LoadingState.LOADING)
//        Espresso.onView(withId(R.id.Articlelist_fragment_layout))
//            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
//    }

    @Test
    fun displayView()
    {
        onView(withId(R.id.rvPeople)).perform(click())
    }
    @Test
    fun test_isRecyclerviewVisible() {
        Espresso.onView(ViewMatchers.withId(R.id.rvPeople)).check(
            ViewAssertions.matches(
                ViewMatchers.isDisplayed()))
    }


    // Assumes that the dialog had a button
        // containing the text "Cancel".
       // onView(withText("Cancel")).check(doesNotExist())
}