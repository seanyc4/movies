package com.seancoyle.movies.framework.presentation

import androidx.fragment.app.FragmentFactory
import com.seancoyle.movies.framework.presentation.moviedetail.MovieDetailFragment
import com.seancoyle.movies.framework.presentation.movielist.MoviesListFragment
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import javax.inject.Inject
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@FlowPreview
@Singleton
class TestMovieFragmentFactory
@Inject
constructor() : FragmentFactory() {

    lateinit var uiController: UIController

    override fun instantiate(classLoader: ClassLoader, className: String) =

        when (className) {

            MoviesListFragment::class.java.name -> {
                val fragment = MoviesListFragment()
                if (::uiController.isInitialized) {
                    fragment.setUIController(uiController)
                }
                fragment
            }

            MovieDetailFragment::class.java.name -> {
                val fragment = MovieDetailFragment()
                if (::uiController.isInitialized) {
                    fragment.setUIController(uiController)
                }
                fragment
            }

            else -> {
                super.instantiate(classLoader, className)
            }
        }

}