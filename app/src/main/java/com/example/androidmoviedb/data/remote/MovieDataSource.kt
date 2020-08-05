package com.example.androidmoviedb.data.remote

import javax.inject.Inject

class MovieDataSource @Inject constructor(
    private val movieService: MovieService
) : BaseDataSource() {

    suspend fun getLatestMovies(key: String) = getResult { movieService.getLatestMovies(key) }
    suspend fun getMovie(key: String, id: Int) = getResult { movieService.getMovie(key, id) }

}