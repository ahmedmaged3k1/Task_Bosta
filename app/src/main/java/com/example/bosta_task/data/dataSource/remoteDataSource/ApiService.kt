package com.example.bionic_time.data.dataSource.remoteDataSource

import com.example.bionic_time.data.dataSource.remoteDataSource.entities.Albums
import com.example.bionic_time.data.dataSource.remoteDataSource.entities.Photos
import com.example.bionic_time.data.dataSource.remoteDataSource.entities.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET

interface ApiService {
    @GET("users")
    suspend fun getUsers(): Response<List<User>>
    @GET("albums")
    suspend fun getUserAlbum(@Body userId: Int): Response<List<Albums>>
    @GET("photos")
    suspend fun getUserPhotos(@Body albumId: Int): Response<List<Photos>>

}