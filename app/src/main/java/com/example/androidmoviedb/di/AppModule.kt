package com.example.androidmoviedb.di

import android.content.Context
import com.example.androidmoviedb.data.local.MovieDao
import com.example.androidmoviedb.data.local.MovieDatabase
import com.example.androidmoviedb.data.remote.MovieDataSource
import com.example.androidmoviedb.data.remote.MovieService
import com.example.androidmoviedb.data.repository.MovieRepository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson) : Retrofit = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun provideMovieService(retrofit: Retrofit): MovieService =
        retrofit.create(MovieService::class.java)

    @Singleton
    @Provides
    fun provideMovieRemoteDataSource(movieService: MovieService) =
        MovieDataSource(movieService)

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) =
        MovieDatabase.getDatabase(appContext)

    @Singleton
    @Provides
    fun provideMovieDao(db: MovieDatabase) = db.movieDao()

    @Singleton
    @Provides
    fun provideRepository(remoteDataSource: MovieDataSource,
                          localDataSource: MovieDao) =
        MovieRepository(remoteDataSource, localDataSource)

}