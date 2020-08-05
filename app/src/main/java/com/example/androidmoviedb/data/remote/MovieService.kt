package com.example.androidmoviedb.data.remote

import com.example.androidmoviedb.data.entities.Movie
import com.example.androidmoviedb.data.entities.MovieList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {

    @GET("/movie/{id}")
    fun getMovie(@Query("api_key") key: String,
                 @Path("id") id: Int): Response<Movie>

    @GET("/movie/latest")
    fun getLatestMovies(@Query("api_key") key: String): Response<MovieList>
}