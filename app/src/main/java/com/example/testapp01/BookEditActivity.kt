package com.example.testapp01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.testapp01.Models.Book
import com.example.testapp01.Repositories.BooksRepository
import com.example.testapp01.databinding.ActivityBookEditBinding

class BookEditActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBookEditBinding

    private val _booksService = BooksRepository.service

    var book: Book? = null

    var bookId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bookId = intent.getIntExtra("bookId", 0)

        try {
            val response = _booksService.getBook(bookId.toString()).execute()
            book = response.body()
        } catch (e: Exception) {
            Log.d("TestApp01", "debug $e")
            throw Exception(e)
        }

        binding.bookTitle.setText(book?.title)
        binding.bookCategory.setText(book?.category)
        binding.bookComment.setText(book?.comment)

        binding.saveButton.setOnClickListener {
            val post = Book(
                id = bookId,
                title = binding.bookTitle.text.toString(),
                categoryId = 1,
                comment = binding.bookComment.text.toString(),
                category = "カテゴリA"
            )
            _booksService.putBooks(bookId.toString(), post).execute()
            finish()
        }

        binding.backButton.setOnClickListener {
            finish()
        }
    }
}