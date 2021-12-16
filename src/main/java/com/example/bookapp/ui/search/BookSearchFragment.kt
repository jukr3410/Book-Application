package com.example.bookapp.ui.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.bookapp.R
import com.example.bookapp.databinding.BookSearchFragmentBinding
import com.example.bookapp.ui.BookViewModel
import com.example.bookapp.ui.books.BooksFragmentDirections
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookSearchFragment : Fragment(R.layout.book_search_fragment) {

    private var _binding: BookSearchFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: BookViewModel by viewModels()
    private lateinit var bookAdapter: BookSearchAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = BookSearchFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUI()
    }

    private fun setUI() {
        bookAdapter = BookSearchAdapter()

        binding.apply {
            rvBook.adapter = bookAdapter

            icBack.setOnClickListener { mView->
                val direction = BookSearchFragmentDirections.actionSearchFragmentToBooksFragment()
                mView.findNavController().navigate(direction)
            }
        }

        viewModel.bookResponse.observe(requireActivity(),
            { result ->
                bookAdapter.books = result.results
            })

    }
}