package com.example.bookapp.data.repository

import com.example.bookapp.data.api.ApiService
import javax.inject.Inject


class BookRepository
@Inject constructor(private val apiService: ApiService) {
    suspend fun getBooks() = apiService.getBooks()
}