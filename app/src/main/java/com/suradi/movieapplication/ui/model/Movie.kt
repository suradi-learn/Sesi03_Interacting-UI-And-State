package com.suradi.movieapplication.ui.model

data class Movie (
    val title: String,
    val description: String,
    val rating: Double,
    val genre: String,
    val director: String,
    val releaseYear: Int,
    val posterPath: String = "",
    val posterResId: Int = 0,
    val id: Int = -1,
    val isLiked: Boolean = false

)
