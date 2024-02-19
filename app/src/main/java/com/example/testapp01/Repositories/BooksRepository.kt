package com.example.testapp01.Repositories

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object BooksRepository {
    private
//    const val BASE_URL = "https://localhost:7057/api/"
    const val BASE_URL = "http://10.0.2.2:5205/api/"

    val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(BASE_URL)
        .build()

    val service: IBooksService = retrofit.create(IBooksService::class.java)
}