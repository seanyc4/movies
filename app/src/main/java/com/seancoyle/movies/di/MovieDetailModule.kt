package com.seancoyle.movies.di

import com.seancoyle.movies.business.data.cache.abstraction.moviedetail.MovieDetailCacheDataSource
import com.seancoyle.movies.business.data.cache.implementation.moviedetail.MovieDetailCacheDataSourceImpl
import com.seancoyle.movies.business.data.network.abstraction.moviedetail.MovieDetailNetworkDataSource
import com.seancoyle.movies.business.data.network.implementation.moviedetail.MovieDetailNetworkDataSourceImpl
import com.seancoyle.movies.business.domain.model.moviedetail.MovieDetailFactory
import com.seancoyle.movies.business.interactors.moviedetail.*
import com.seancoyle.movies.framework.datasource.cache.abstraction.moviedetail.MovieDetailDaoService
import com.seancoyle.movies.framework.datasource.cache.database.Database
import com.seancoyle.movies.framework.datasource.cache.dao.moviedetail.MovieDetailDao
import com.seancoyle.movies.framework.datasource.cache.implementation.moviedetail.MovieDetailDaoServiceImpl
import com.seancoyle.movies.framework.datasource.cache.mappers.moviedetail.MovieDetailCacheMapper
import com.seancoyle.movies.framework.datasource.network.abstraction.moviedetail.MovieDetailRetrofitService
import com.seancoyle.movies.framework.datasource.network.api.moviedetail.MovieDetailApi
import com.seancoyle.movies.framework.datasource.network.implementation.moviedetail.MovieCastRetrofitServiceImpl
import com.seancoyle.movies.framework.datasource.network.mappers.moviedetail.MovieDetailNetworkMapper
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import retrofit2.Retrofit
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@FlowPreview
@Module
object MovieDetailModule {

    @Singleton
    @Provides
    fun provideMovieDetailInteractors(
        movieDetailCacheDataSource: MovieDetailCacheDataSource,
        movieDetailNetworkDataSource: MovieDetailNetworkDataSource,
        movieDetailFactory: MovieDetailFactory
    ): MovieDetailInteractors {
        return MovieDetailInteractors(
            deleteMovieCast = DeleteMovieCast(
                cacheDataSource = movieDetailCacheDataSource
            ),
            deleteMultipleMovieCasts = DeleteMultipleMovieCasts(
                cacheDataSource = movieDetailCacheDataSource
            ),
            getAllMovieCastFromCache = GetAllMovieCastFromCache(
                cacheDataSource = movieDetailCacheDataSource
            ),
            getMovieCastByIdFromCache = GetMovieCastByIdFromCache(
                cacheDataSource = movieDetailCacheDataSource
            ),
            getMoviesCastFromNetworkAndInsertToCache = GetMovieCastFromNetworkAndInsertToCache(
                cacheDataSource = movieDetailCacheDataSource,
                networkDataSource = movieDetailNetworkDataSource,
                factory = movieDetailFactory
            ),
            getNumMoviesCast = GetNumMoviesCast(
                cacheDataSource = movieDetailCacheDataSource
            ),
            insertMovieCast = InsertMovieCast(
                movieDetailCacheDataSource = movieDetailCacheDataSource,
                movieDetailFactory = movieDetailFactory
            )
        )
    }

    @Singleton
    @Provides
    fun provideMovieDetailNetworkDataSource(
        movieDetailRetrofitService: MovieDetailRetrofitService
    ): MovieDetailNetworkDataSource {
        return MovieDetailNetworkDataSourceImpl(
            retrofitService = movieDetailRetrofitService
        )
    }

    @Singleton
    @Provides
    fun provideMovieDetailRetrofitService(
        api: MovieDetailApi,
        networkMapper: MovieDetailNetworkMapper
    ): MovieDetailRetrofitService {
        return MovieCastRetrofitServiceImpl(
            api = api,
            networkMapper = networkMapper
        )
    }

    @Singleton
    @Provides
    fun provideMovieDetailApi(
        retrofit: Retrofit
    ): MovieDetailApi {
        return retrofit.create(MovieDetailApi::class.java)
    }

    @Singleton
    @Provides
    fun provideMovieDetailNetworkMapper(): MovieDetailNetworkMapper {
        return MovieDetailNetworkMapper()
    }

    @Singleton
    @Provides
    fun provideMovieDetailFactory(): MovieDetailFactory {
        return MovieDetailFactory()
    }

    @Singleton
    @Provides
    fun provideMovieDetailDao(database: Database): MovieDetailDao {
        return database.movieDetailDao()
    }

    @Singleton
    @Provides
    fun provideMovieDetailCacheMapper(): MovieDetailCacheMapper {
        return MovieDetailCacheMapper()
    }

    @Singleton
    @Provides
    fun provideMovieDetailDaoService(
        dao: MovieDetailDao,
        movieDetailCacheMapper: MovieDetailCacheMapper
    ): MovieDetailDaoService {
        return MovieDetailDaoServiceImpl(
            dao = dao,
            cacheMapper = movieDetailCacheMapper
        )
    }

    @Singleton
    @Provides
    fun provideMovieDetailCacheDataSource(
        daoService: MovieDetailDaoService
    ): MovieDetailCacheDataSource {
        return MovieDetailCacheDataSourceImpl(
            daoService = daoService
        )
    }

}