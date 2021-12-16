package com.example.bookapp.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.bookapp.R
import com.example.bookapp.databinding.DetailFragmentBinding
import com.example.bookapp.model.Book
import android.content.Intent
import android.net.Uri


class DetailFragment : Fragment(R.layout.detail_fragment) {

    private var _binding: DetailFragmentBinding? = null
    private val binding get() = _binding!!
    private val args: DetailFragmentArgs by navArgs()
    private lateinit var book: Book

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DetailFragmentBinding.inflate(
            inflater, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        book = args.book
        setUI()
    }

    private fun setUI() {
        binding.apply {

            tvTitle.text = book.title
            bookImage.load(book.multimedia[0].url) {
                crossfade(true)
                crossfade(500)
            }

            icBack.setOnClickListener { mView->
                val direction = DetailFragmentDirections.actionDetailFragmentToBooksFragment()
                mView.findNavController().navigate(direction)
            }

            btnOpenWeb.setOnClickListener { mView ->
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(book.url))
                startActivity(browserIntent)
            }

        }
    }

}