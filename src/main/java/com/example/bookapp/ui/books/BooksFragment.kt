package com.example.bookapp.ui.books

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.bookapp.R
import com.example.bookapp.databinding.BooksFragmentBinding
import com.example.bookapp.ui.BookViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class BooksFragment : Fragment(R.layout.books_fragment) {

    private var _binding: BooksFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: BookViewModel by viewModels()
    private lateinit var bookAdapter: BookAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = BooksFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUI()
    }

    private fun setUI() {
        bookAdapter = BookAdapter()
        binding.apply {
            rvBook.layoutManager = GridLayoutManager(activity, 2)
            rvBook.adapter = bookAdapter

            icSearch.setOnClickListener { mView->
                val direction = BooksFragmentDirections.actionBooksFragmentToSearchFragment()
                mView.findNavController().navigate(direction)
            }
        }

        viewModel.bookResponse.observe(requireActivity(),
            { result ->
                bookAdapter.books = result.results
            })


    }

}