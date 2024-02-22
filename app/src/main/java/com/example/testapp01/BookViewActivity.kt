package com.example.testapp01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.example.testapp01.databinding.ActivityBookViewBinding

class BookViewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBookViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bookId = intent.getStringExtra("bookId")
        val bookTitle = intent.getStringExtra("bookTitle")
        val bookCategory = intent.getStringExtra("bookCategory")
        val bookComment = intent.getStringExtra("bookComment")

        binding.bookTitle.text = bookTitle.toString()
        binding.bookCategory.text = bookCategory.toString()
        binding.bookComment.text = bookComment.toString()

        binding.backButton.setOnClickListener {
            finish()
        }
    }
}