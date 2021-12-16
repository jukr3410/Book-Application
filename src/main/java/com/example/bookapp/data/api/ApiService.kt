package com.example.bookapp.data.api

import com.example.bookapp.Constants
import com.example.bookapp.model.BookResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("books.json?api-key="+Constants.API_KEY)
    suspend fun getBooks(
    ): Response<BookResponse>
}