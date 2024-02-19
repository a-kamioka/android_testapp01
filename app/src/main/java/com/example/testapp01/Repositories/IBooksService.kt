package com.example.testapp01.Repositories

import com.example.testapp01.Models.Books
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface IBooksService {

    @GET("books")
    fun getBooks(): Call<List<Books>>

    @POST("books")
    fun postBooks(@Body body: RequestBody): Call<ResponseBody>

    @PUT("books/{id}")
    fun putBooks(@Path("id") id:String, @Body body: RequestBody): Call<ResponseBody>

    @DELETE("books/{id}")
    fun deleteBooks(@Path("id") id:String): Call<ResponseBody>
}