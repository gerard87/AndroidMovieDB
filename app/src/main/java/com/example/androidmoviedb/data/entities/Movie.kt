package com.example.androidmoviedb.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class Movie (
    @PrimaryKey
    val id: Int,
    val overview: String,
    val image: String,
    val release_date: String,
    val title: String,
    val score: Double,
    val vote_count: Int
)