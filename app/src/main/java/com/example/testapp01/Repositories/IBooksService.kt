package com.example.testapp01.Repositories

import com.example.testapp01.Models.Book
import com.example.testapp01.Models.Category
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface IBooksService {

    @GET("books")
    fun getBooks(): Call<List<Book>>

    @GET("books/{id}")
    fun getBook(@Path("id") id: String): Call<Book>

    @POST("books")
    fun postBooks(@Body book: Book): Call<ResponseBody>

    @PUT("books/{id}")
    fun putBooks(@Path("id") id:String, @Body book: Book): Call<ResponseBody>

    @DELETE("books/{id}")
    fun deleteBooks(@Path("id") id:String): Call<ResponseBody>

    @GET("categories")
    fun getCategories(): Call<List<Category>>
}