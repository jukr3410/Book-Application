package com.example.bookapp.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookapp.data.repository.BookRepository
import com.example.bookapp.model.BookResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookViewModel
@Inject
constructor(private val repository: BookRepository) : ViewModel() {

    private val _response = MutableLiveData<BookResponse>()
    val bookResponse: LiveData<BookResponse>

    get() = _response

    init {
        getBooks()
    }

    private fun getBooks() = viewModelScope.launch {
        repository.getBooks().let { response ->
            if (response.isSuccessful) {
                _response.postValue(response.body())
            } else {
                Log.d("response", "error: ${response.code()}")
            }
        }
    }
}