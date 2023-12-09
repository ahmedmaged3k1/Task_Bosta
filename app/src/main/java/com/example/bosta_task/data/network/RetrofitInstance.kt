package com.example.bionic_time.data.network

import com.example.bosta_task.data.dataSource.remoteDataSource.ApiService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {
    private val retrofit by lazy {
        val okHttpClient = OkHttpClient.Builder()
            .build()
        Retrofit.Builder().baseUrl(Credentials.baseUrl).client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }
    private val api by lazy {
        retrofit.create(ApiService::class.java)
    }
}