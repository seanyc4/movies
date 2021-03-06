package com.seancoyle.movies.business.data.cache.implementation.moviedetail

import com.seancoyle.movies.business.data.cache.abstraction.moviedetail.MovieDetailCacheDataSource
import com.seancoyle.movies.business.domain.model.moviedetail.MovieCastDomainEntity
import com.seancoyle.movies.framework.datasource.cache.abstraction.moviedetail.MovieDetailDaoService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieDetailCacheDataSourceImpl
@Inject
constructor(
    private val daoService: MovieDetailDaoService
) : MovieDetailCacheDataSource {


    override suspend fun insert(movieCast: MovieCastDomainEntity): Long {
        return daoService.insert(
            movieCast = movieCast
        )
    }

    override suspend fun insertList(castList: List<MovieCastDomainEntity>): LongArray {
        return daoService.insertList(
            castList = castList
        )
    }

    override suspend fun deleteById(id: Int): Int {
        return daoService.deleteById(
            id = id
        )
    }

    override suspend fun deleteList(castList: List<MovieCastDomainEntity>): Int {
        return daoService.deleteList(
            castList = castList
        )
    }

    override suspend fun deleteAll() {
        return daoService.deleteAll()
    }

    override suspend fun getAll(): List<MovieCastDomainEntity>? {
        return daoService.getAll()
    }

    override suspend fun getById(id: Int): MovieCastDomainEntity? {
        return daoService.getById(
            id = id
        )
    }

    override suspend fun getTotalEntries(): Int {
        return daoService.getTotalEntries()
    }

}





















