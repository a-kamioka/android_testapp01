package com.example.testapp01.Models

import androidx.lifecycle.ViewModel

data class Books(
    val id: Int,
    val title: String,
    val categoryId: Int,
    val comment: String?
)