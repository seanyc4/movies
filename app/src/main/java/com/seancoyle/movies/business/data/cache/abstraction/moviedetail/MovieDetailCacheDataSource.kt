package com.seancoyle.movies.business.data.cache.abstraction.moviedetail

import com.seancoyle.movies.business.domain.model.moviedetail.MovieCast

interface MovieDetailCacheDataSource {

    suspend fun insert(movieCast: MovieCast): Long

    suspend fun deleteById(id: Int): Int

    suspend fun deleteList(castList: List<MovieCast>): Int

    suspend fun deleteAll()

    suspend fun getById(id: Int): MovieCast?

    suspend fun getAll(): List<MovieCast>?

    suspend fun getTotalEntries(): Int

    suspend fun insertList(castList: List<MovieCast>): LongArray
}






