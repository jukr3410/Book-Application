package com.example.bookapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Book(
    val title: String,
    val abstract: String,
    val byLine: String,
    val url: String,
    val multimedia: List<Multimedia>
):Parcelable

