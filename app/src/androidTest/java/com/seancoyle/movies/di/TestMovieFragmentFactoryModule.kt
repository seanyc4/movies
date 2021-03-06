package com.seancoyle.movies.di

import androidx.fragment.app.FragmentFactory
import com.seancoyle.movies.framework.presentation.TestMovieFragmentFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import javax.inject.Singleton

@FlowPreview
@ExperimentalCoroutinesApi
@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [MovieFragmentFactoryModule::class]
)
object TestMovieFragmentFactoryModule {

    @Singleton
    @Provides
    fun provideMovieFragmentFactory(
    ): FragmentFactory {
        return TestMovieFragmentFactory()
    }
}