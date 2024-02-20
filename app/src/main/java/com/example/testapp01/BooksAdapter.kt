package com.example.testapp01

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.testapp01.Models.Books
import com.example.testapp01.databinding.ListBooksBinding

class BooksAdapter(val context: Context, val books: List<Books>): BaseAdapter() {

    private val inflater = LayoutInflater.from(context)

    override fun getCount(): Int {
        return books.count()
    }

    override fun getItem(position: Int): Any {
        return books[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val binding: ListBooksBinding
        binding = ListBooksBinding.inflate(inflater, parent, false)
        binding.bookTitle.text = books[position].title
        return binding.root
    }
}