package com.example.myapplication.view


import androidx.lifecycle.MediatorLiveData
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.util.UIState

import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock

@RunWith(AndroidJUnit4ClassRunner::class)
class ArticleListFragmentTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Mock
    val fakeLoadingState= MediatorLiveData<UIState>()

    @Test
    fun test_mainActivityIsDisplayed() {
        emitLoadingState(UIState.Loading)
        Espresso.onView(withId(R.id.mainActivity)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun test_isFragmentArticleListVisible() {
        emitLoadingState(UIState.Loading)
        Espresso.onView(withId(R.id.peopleFrag))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun test_isRecyclerviewVisible() {
        Espresso.onView(ViewMatchers.withId(R.id.rvPeople)).check(
            ViewAssertions.matches(
                ViewMatchers.isDisplayed()))
    }

//    @Test
//    fun test_isProgressBarNotShowingWhenSuccess() {
//        emitLoadingState(UIState.Success<T>())
//        Espresso.onView(withId(R.id.progress)).check(
//            ViewAssertions.matches(
//                ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.GONE)))
//    }

    private fun emitLoadingState(loadingState: UIState){
        fakeLoadingState.postValue(loadingState)
    }

}
