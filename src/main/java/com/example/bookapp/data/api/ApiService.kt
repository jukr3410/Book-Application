package com.example.bookapp.data.api

import com.example.bookapp.model.BookResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("books.json?api-key=GDrQ2aBDKGj6DELALg9H4JeXLysN1peW")
    suspend fun getBooks(): Response<BookResponse>
}