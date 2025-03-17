package com.example.bookshelf.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookshelf.data.BooksRepository
import com.example.bookshelf.model.BookItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed interface BooksUiState {
    data class Success(val books: List<BookItem>) : BooksUiState
    object Loading : BooksUiState
    object Error : BooksUiState
}

class BooksViewModel : ViewModel() {

    private val repository = BooksRepository()

    private val _uiState = MutableStateFlow<BooksUiState>(BooksUiState.Loading)
    val uiState: StateFlow<BooksUiState> = _uiState

    init {
        getBooks()
    }

    private fun getBooks(query: String = "jazz history") {
        viewModelScope.launch {
            try {
                val books = repository.getBooks(query)
                _uiState.value = BooksUiState.Success(books)
            } catch (e: Exception) {
                _uiState.value = BooksUiState.Error
            }
        }
    }
}
