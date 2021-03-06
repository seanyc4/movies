package com.seancoyle.movies.framework.datasource.cache.dao.moviedetail

import androidx.room.*
import com.seancoyle.movies.framework.datasource.cache.model.moviedetail.MovieCastCacheEntity

@Dao
interface MovieDetailDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movie: MovieCastCacheEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertList(castList: List<MovieCastCacheEntity>): LongArray

    @Query("DELETE FROM movie_cast WHERE id = :id")
    suspend fun deleteById(id: Int): Int

    @Query("DELETE FROM movie_cast WHERE id IN (:ids)")
    suspend fun deleteList(ids: List<Int>): Int

    @Query("DELETE FROM movie_cast")
    suspend fun deleteAll()

    @Query("SELECT * FROM movie_cast WHERE id = :id")
    suspend fun get(id: Int): MovieCastCacheEntity?

    @Query("SELECT * FROM movie_cast")
    suspend fun getAll(): List<MovieCastCacheEntity>?

    @Query("SELECT COUNT(*) FROM movie_cast")
    suspend fun getTotalEntries(): Int

}












