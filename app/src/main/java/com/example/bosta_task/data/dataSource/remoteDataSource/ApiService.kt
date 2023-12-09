package com.example.bosta_task.data.dataSource.remoteDataSource

import com.example.bosta_task.data.dataSource.remoteDataSource.entities.Albums
import com.example.bosta_task.data.dataSource.remoteDataSource.entities.Photos
import com.example.bosta_task.data.dataSource.remoteDataSource.entities.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("users")
    suspend fun getUsers(): Response<List<User>>
    @GET("albums")
    suspend fun getUserAlbum(@Query ("userId") userId: Int): Response<List<Albums>>
    @GET("photos")
    suspend fun getUserPhotos(@Query ("albumId") albumId: Int): Response<List<Photos>>

}