package com.example.bookapp.ui.books

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.bookapp.databinding.ItemViewHolderBinding
import com.example.bookapp.model.Book


class BookAdapter : RecyclerView.Adapter<BookAdapter.ItemViewHolder>() {

    inner class ItemViewHolder(val binding: ItemViewHolderBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val diffCallback = object : DiffUtil.ItemCallback<Book>() {
        override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean {
            return oldItem == newItem
        }


    }

    private val differ = AsyncListDiffer(this, diffCallback)

    var books: List<Book>
        get() = differ.currentList
        set(value) {
            differ.submitList(value)
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            ItemViewHolderBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val book = books[position]

        holder.binding.apply {

            tvTitle.text = book.title
            bookImage.load(book.multimedia[0].url) {
                crossfade(true)
                crossfade(500)
            }

        }

        holder.itemView.setOnClickListener { mView->
            val direction = BooksFragmentDirections.actionBooksFragmentToDetailFragment(book)
            mView.findNavController().navigate(direction)
        }

    }

    override fun getItemCount() = books.size

}