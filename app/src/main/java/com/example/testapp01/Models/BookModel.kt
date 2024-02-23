package com.example.testapp01.Models

data class Book(
    val id: Int,
    val title: String,
    val categoryId: Int,
    val comment: String?,
    val category: String? = null
)

data class Category(
    val id: Int,
    val name: String
) {
    override fun toString(): String {
        return name
    }
}