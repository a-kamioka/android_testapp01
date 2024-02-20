package com.example.testapp01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import android.util.Log
import com.example.testapp01.Repositories.BooksRepository
import com.example.testapp01.Models.Books

import com.example.testapp01.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val _booksService = BooksRepository.service

    var books: MutableList<Books>? = mutableListOf()

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
}