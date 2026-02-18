package com.suradi.movieapplication.ui.view


import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.foundation.Image
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.foundation.layout.heightIn
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter


@Composable
fun AddMovieView() {

    var title by remember { mutableStateOf(value = "") }
    var description by remember { mutableStateOf(value = "") }
    var selectedRating by remember { mutableStateOf(value = "4.0") }
    var genre by remember { mutableStateOf(value = "") }
    var director by remember { mutableStateOf(value = "") }
    var releaseYear by remember { mutableStateOf(value = "") }
    var selectedImageUri by remember { mutableStateOf <Uri?>(value = null) }

    var showDialog by remember { mutableStateOf(value = false) }
    var rating by remember { mutableStateOf(value = 3) }
    val scrollState = rememberScrollState()

    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    )  { uri: Uri? ->
         selectedImageUri = uri
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = "Add New Movie",
            style = MaterialTheme.typography.headlineSmall.copy(fontSize = 22.sp)
        )

        // Image Picker
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
                .background(Color.LightGray, shape = RoundedCornerShape(8.dp))
                .clickable { galleryLauncher.launch(input = "image/*") },
            contentAlignment = Alignment.Center
        ) {
            if (selectedImageUri != null) {
                Image(
                    painter = rememberAsyncImagePainter(model = selectedImageUri),
                    contentDescription = "Selected Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(shape = RoundedCornerShape(8.dp))
                )
            } else {
                Text(text = "Tap to choose poster", color = Color.DarkGray)
            }
        }

        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text(text = "Title")},
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { Text(text = "Description")},
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 120.dp), // Make it taller
            maxLines = 10, // Allow more lines
            singleLine = false
        )

        OutlinedTextField(
            value = genre,
            onValueChange = { genre = it },
            label = { Text(text = "Genre")},
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = director,
            onValueChange = { director = it },
            label = { Text(text = "Director")},
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = releaseYear,
            onValueChange = { releaseYear = it },
            label = { Text(text = "Release Year")},
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number)

        )

        // Rating Dropdown
        Text(text = "Rating", modifier = Modifier.padding(top = 8.dp))

        StarRatingBar(
            rating = rating, onRatingChange = { rating = it })

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { showDialog = true }, modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Submit")
        }
    }

    // Dialog to show movie details
    if (showDialog) {
        AlertDialog(onDismissRequest = { showDialog = false}, confirmButton = {
            TextButton(onClick = { showDialog = false}) {
                Text( text = "Close")
            }
        }, title = { Text(text = title.ifBlank { "Movie Detail"}) }, text = {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                if (selectedImageUri != null) {
                    Image(
                        painter = rememberAsyncImagePainter(model = selectedImageUri),
                        contentDescription = title,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(150.dp)
                            .clip(shape = RoundedCornerShape(8.dp))
                    )
                }
                Text( text = "Description: $description")
                Text( text = "Rating: $selectedRating")
                Text( text = "Genre: $genre")
                Text( text = "Director: $director")
                Text( text = "Year: ${releaseYear.ifBlank { "N/A" }}")
                Text( text = "Description: $description")
            }
        })
    }

}

@Composable
fun StarRatingBar(
    rating: Int, onRatingChange: (Int) -> Unit, maxRating: Int = 5
) {
    Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
        for (i in 1..maxRating) {
            Icon(
                imageVector = if (i <= rating) Icons.Default.Star else Icons.Default.StarBorder,
                contentDescription = "Star $i",
                tint = Color(0xFFFFC107),
                modifier = Modifier
                    .size(40.dp)
                    .clickable { onRatingChange(i)})
        }
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
private fun AddMoviePreview() {
    AddMovieView()
}
