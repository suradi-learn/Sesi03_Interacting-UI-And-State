package com.suradi.movieapplication.ui.view

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.suradi.movieapplication.ui.model.DummyMovieData
import com.suradi.movieapplication.ui.model.Movie
import kotlinx.serialization.internal.MapLikeSerializer

@Composable
fun MovieCard(
    movie: Movie,
    onToggleLike: () -> Unit = {},
    modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)

    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            // poster dari resource
            Image(
                painter = painterResource(id = movie.posterResId),
                contentDescription = movie.title,
                modifier = Modifier
                    .width(100.dp)
                    .aspectRatio(0.675f)
                    .clip(shape = RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Fit

            )

            Spacer(modifier = Modifier.width(12.dp))

            // Movie Info
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterVertically)

            ) {
                Row (
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = movie.title,
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.weight(1f)
                    )

                    IconButton(onClick = onToggleLike) {
                        Icon(
                            imageVector = Icons.Filled.Favorite,
                            contentDescription = if (movie.isLiked) "Unlike" else "Like",
                            tint = if (movie.isLiked) Color(color = 0xFFFF4081) else Color.Gray // pink vs gray
                        )
                    }

                }

                Text(
                    text = "${movie.genre} . ${movie.releaseYear}",
                    style = MaterialTheme.typography.bodySmall.copy(color = Color.Gray)
                )

                Spacer(Modifier.height(4.dp))

                // Rating
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Rating",
                        tint = Color(color = 0xFFFFC107), // gold
                        modifier = Modifier.size(16.dp)
                    )
                    Text(
                        text = movie.rating.toString(),
                        style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Medium),
                        modifier = Modifier.padding(start = 4.dp)
                    )

                }

                Spacer(Modifier.height(6.dp))
                Text(
                    text = movie.description,
                    style = MaterialTheme.typography.bodySmall,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }

}

@Composable
@Preview(showBackground = true)
private fun MovieCardPreview() {
    MovieCard(movie = DummyMovieData.movies[1])
}
