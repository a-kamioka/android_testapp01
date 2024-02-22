package com.example.testapp01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import com.example.testapp01.Repositories.BooksRepository
import com.example.testapp01.Models.Book

import com.example.testapp01.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val _booksService = BooksRepository.service

    var books: MutableList<Book>? = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.Builder()
            .permitAll()
            .build())

//        binding.addButton.setOnClickListener {
//
//        }
    }

    override fun onResume() {
        super.onResume()

        getBooks()

        val listView = binding.booksList
        listView.adapter = books?.let { BooksAdapter(this, it.toList()) }
        listView.onItemClickListener = ListItemClick()
    }

    private fun getBooks() {
        try {
            val response = _booksService.getBooks().execute()
            books = response.body()?.toMutableList()
        } catch (e: Exception) {
            Log.d("TestApp01", "debug $e")
            throw Exception(e)
        }
    }

    private inner class ListItemClick: AdapterView.OnItemClickListener {
        override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            val item = parent?.getItemAtPosition(position) as Book

            val intentBookView = Intent(this@MainActivity, BookViewActivity::class.java)
            intentBookView.putExtra("bookId", item.id)
            intentBookView.putExtra("bookTitle", item.title)
            intentBookView.putExtra("bookCategory", item.category)
            intentBookView.putExtra("bookComment", item.comment)
            startActivity(intentBookView)
        }
    }
}