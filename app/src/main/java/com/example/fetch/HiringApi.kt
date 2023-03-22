package com.example.fetch

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface HiringApi {

    @GET("/hiring.json")
    suspend fun getData(): Response<List<Hiring>>

    companion object{
        operator fun invoke():HiringApi{
            val api:HiringApi by lazy{
                        Retrofit.Builder()
                        .baseUrl("https://fetch-hiring.s3.amazonaws.com")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                        .create(HiringApi::class.java)
            }
            return api
        }
    }
}