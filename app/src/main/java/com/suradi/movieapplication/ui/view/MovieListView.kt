package com.suradi.movieapplication.ui.view

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.suradi.movieapplication.ui.model.DummyMovieData
import com.suradi.movieapplication.ui.model.Movie

@Composable
fun MovieListView(movies: List<Movie> = DummyMovieData.movies) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(4.dp)
    ) {
        items (movies) { movie ->
            MovieCard(movie = movie)
        }
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
private fun MovieListPreview() {
    MovieListView()
}