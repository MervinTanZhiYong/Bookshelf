package com.example.bookshelf

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.bookshelf.ui.theme.BookshelfTheme
import com.example.bookshelf.uicompose.BookshelfHomeScreen
import com.example.bookshelf.viewmodel.BooksViewModel

class MainActivity : ComponentActivity() {

    // Initialize ViewModel using viewModels() delegate
    private val viewModel: BooksViewModel by viewModels()

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() // Optional, keeps edge-to-edge behavior
        setContent {
            // Apply the app theme
            BookshelfTheme {
                // App scaffold to add top bar and content
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text("Bookshelf") }
                        )
                    }
                ) { paddingValues ->
                    // Pass ViewModel to HomeScreen and apply padding
                    BookshelfHomeScreen(viewModel = viewModel, modifier = Modifier.padding(paddingValues))
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BookshelfTheme {
        Greeting("Android")
    }
}