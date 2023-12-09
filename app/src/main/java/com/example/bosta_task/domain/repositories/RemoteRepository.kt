package com.example.bosta_task.domain.repositories

import com.example.bionic_time.data.dataSource.remoteDataSource.entities.Albums
import com.example.bionic_time.data.dataSource.remoteDataSource.entities.Photos
import com.example.bionic_time.data.dataSource.remoteDataSource.entities.User
import retrofit2.http.Body

interface RemoteRepository {

    suspend fun getUsers(): List<User>

    suspend fun getUserAlbum(@Body userId: Int): List<Albums>

    suspend fun getUserPhotos(@Body albumId: Int): List<Photos>
}