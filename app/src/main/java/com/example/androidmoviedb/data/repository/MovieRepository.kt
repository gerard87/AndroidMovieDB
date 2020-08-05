package com.example.androidmoviedb.data.repository

import com.example.androidmoviedb.data.local.MovieDao
import com.example.androidmoviedb.data.remote.MovieDataSource
import com.example.androidmoviedb.utils.getCall
import javax.inject.Inject

class MovieRepository @Inject constructor (
    private val remoteDataSource: MovieDataSource,
    private val localDatasource: MovieDao
) {

    fun getMovie(key: String, id: Int) = getCall(
        databaseQuery = { localDatasource.getMovie(id) },
        networkCall = { remoteDataSource.getMovie(key, id) },
        saveCallResult = { localDatasource.insert(it) }
    )

    fun getMovies(key: String) = getCall(
        databaseQuery = { localDatasource.getAllMovies() },
        networkCall = { remoteDataSource.getLatestMovies(key) },
        saveCallResult = { localDatasource.insertAll(it.list) }
    )
}