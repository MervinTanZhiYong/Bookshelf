package com.example.bookshelf.uicompose

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.bookshelf.R
import com.example.bookshelf.model.BookItem
import com.example.bookshelf.viewmodel.BooksUiState
import com.example.bookshelf.viewmodel.BooksViewModel

@Composable
fun BookshelfHomeScreen(viewModel: BooksViewModel, modifier: Modifier = Modifier) {
    val uiState by viewModel.uiState.collectAsState()

    Column(modifier = modifier.fillMaxSize()) {
        when (uiState) {
            is BooksUiState.Loading -> LoadingScreen()
            is BooksUiState.Error -> ErrorScreen()
            is BooksUiState.Success -> BooksGridScreen((uiState as BooksUiState.Success).books)
        }
    }
}

@Composable
fun BooksGridScreen(books: List<BookItem>, modifier: Modifier = Modifier) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier.fillMaxSize()
    ) {
        items(books) { book ->
            BookCard(book = book)
        }
    }
}

@Composable
fun BookCard(book: BookItem, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
//            AsyncImage(
//                model = book.volumeInfo.imageLinks?.thumbnail?.replace("http://", "https://") ?: "", // Empty string if null
//                contentDescription = "Book Cover",
//                modifier = Modifier
//                    .height(150.dp)
//                    .fillMaxWidth()
//            )

            AsyncImage(
                model = book.volumeInfo.imageLinks?.thumbnail?.replace("http://", "https://"),
                contentDescription = "Book Cover",
                modifier = Modifier
                    .height(150.dp)
                    .fillMaxWidth(),
                placeholder = painterResource(R.drawable.placeholder) // Add a placeholder in drawable
            )

            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = book.volumeInfo.title, // Accessing title properly
                style = MaterialTheme.typography.titleMedium,
                maxLines = 2
            )
        }
    }
}

@Composable
fun LoadingScreen() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun ErrorScreen() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text("Failed to load books. Please try again.")
    }
}
