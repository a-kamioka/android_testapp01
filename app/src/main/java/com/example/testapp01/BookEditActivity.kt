package com.example.testapp01

import android.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.example.testapp01.Models.Book
import com.example.testapp01.Models.Category
import com.example.testapp01.Repositories.BooksRepository
import com.example.testapp01.databinding.ActivityBookEditBinding

class BookEditActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBookEditBinding

    private val _booksService = BooksRepository.service

    var book: Book? = null
    var bookId: Int = 0
    var categories: List<Category>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bookId = intent.getIntExtra("bookId", 0)
        var categoryId: Int = 0

        val spinner = binding.bookCategory
        categories = _booksService.getCategories().execute().body()
        val adapter = ArrayAdapter(this, R.layout.simple_spinner_item, categories!!)
        spinner.adapter = adapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedItem = parent?.selectedItem as Category
                categoryId = selectedItem.id
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        if (bookId > 0) {
            try {
                val response = _booksService.getBook(bookId.toString()).execute()
                book = response.body()
            } catch (e: Exception) {
                Log.d("TestApp01", "debug $e")
                throw Exception(e)
            }

            binding.bookTitle.setText(book?.title)
            spinner.setSelection(findCategory(book?.categoryId))
            binding.bookComment.setText(book?.comment)
        }

        binding.saveButton.setOnClickListener {
            val post = Book(
                id = bookId,
                title = binding.bookTitle.text.toString(),
                categoryId = categoryId,
                comment = binding.bookComment.text.toString(),
            )
            _booksService.putBooks(bookId.toString(), post).execute()
            finish()
        }

        binding.backButton.setOnClickListener {
            finish()
        }
    }

    fun findCategory(categoryId: Int?): Int {
        var index = 0
        while(index < categories!!.count()) {
            if (categories!![index].id == categoryId) {
                return index
            }
            index++
        }
        return index
    }
}