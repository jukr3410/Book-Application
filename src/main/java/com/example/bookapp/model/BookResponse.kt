package com.example.bookapp.model

import com.example.bookapp.model.Book


data class BookResponse(
    val status: String,
    val results: List<Book>
)