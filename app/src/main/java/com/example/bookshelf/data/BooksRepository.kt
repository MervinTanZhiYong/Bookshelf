package com.example.bookshelf.data

import com.example.bookshelf.model.BookItem
import com.example.bookshelf.network.BooksApi

class BooksRepository {
    suspend fun getBooks(query: String): List<BookItem> {
        return BooksApi.retrofitService.getBooks(query).items
    }
}
