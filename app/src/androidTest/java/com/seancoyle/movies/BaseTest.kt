package com.seancoyle.movies

import android.content.Context
import android.view.View
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.IdlingResource
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.seancoyle.movies.util.ViewShownIdlingResource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import org.hamcrest.Matcher


@ExperimentalCoroutinesApi
@FlowPreview
abstract class BaseTest {


    // wait for a certain view to be shown.
    // ex: waiting for splash screen to transition to MoviesListFragment
    fun waitViewShown(matcher: Matcher<View>) {
        val idlingResource: IdlingResource = ViewShownIdlingResource(matcher, isDisplayed())
        try {
            IdlingRegistry.getInstance().register(idlingResource)
            onView(withId(0)).check(doesNotExist())
        } finally {
            IdlingRegistry.getInstance().unregister(idlingResource)
        }
    }
}













