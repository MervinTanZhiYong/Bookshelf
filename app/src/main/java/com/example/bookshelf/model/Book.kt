package com.example.bookshelf.model

import com.google.gson.annotations.SerializedName

data class BooksResponse(
    @SerializedName("items") val items: List<BookItem>
)

data class BookItem(
    @SerializedName("id") val id: String,
    @SerializedName("volumeInfo") val volumeInfo: VolumeInfo
)

data class VolumeInfo(
    @SerializedName("title") val title: String,
    @SerializedName("imageLinks") val imageLinks: ImageLinks?
)

data class ImageLinks(
    @SerializedName("thumbnail") val thumbnail: String
)
