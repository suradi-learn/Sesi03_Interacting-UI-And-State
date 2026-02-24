package com.suradi.movieapplication.ui.view

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.suradi.movieapplication.ui.model.DummyMovieData
import com.suradi.movieapplication.ui.model.Movie
import com.suradi.movieapplication.ui.viewmodel.MovieListViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key

@Composable
fun MovieListView(
    viewModel: MovieListViewModel = viewModel(),
    modifier: Modifier = Modifier
) {
    val movies by viewModel.movies.collectAsState()

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(4.dp)
    ) {
        items (movies, key = {it.title}) { movie ->
            MovieCard(movie = movie, onToggleLike = {viewModel.toggleIsLiked(movie)})
        }
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
private fun MovieListPreview() {
    MovieListView()
}