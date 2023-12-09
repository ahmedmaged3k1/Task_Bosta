package com.example.bionic_time.data.dataSource.remoteDataSource

import android.content.ContentValues
import android.util.Log
import com.example.bionic_time.data.dataSource.remoteDataSource.entities.Albums
import com.example.bionic_time.data.dataSource.remoteDataSource.entities.Photos
import com.example.bionic_time.data.dataSource.remoteDataSource.entities.User
import com.example.bosta_task.domain.repositories.RemoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RemoteRepositoryImp @Inject constructor(private val apiService: ApiService) :
    RemoteRepository {
    private lateinit var usersList: List<User>
    private lateinit var albumsList: List<Albums>
    private lateinit var photosList: List<Photos>

    override suspend fun getUsers(): List<User> {
        withContext(Dispatchers.Default) {
            try {
                val response = apiService.getUsers().body() ?: listOf()
                usersList = response
                Log.d(ContentValues.TAG, "getAllUsers: success ${usersList.toString()}")

            } catch (e: Exception) {
                e.printStackTrace()
                Log.d(ContentValues.TAG, "getAllUsers:  error ")
            }
        }
        return usersList
    }

    override suspend fun getUserAlbum(userId: Int): List<Albums> {
        withContext(Dispatchers.Default) {
            try {
                val response = apiService.getUserAlbum(userId).body() ?: listOf()
                albumsList = response
                Log.d(ContentValues.TAG, "getAllAlbums: success ${albumsList.toString()}")

            } catch (e: Exception) {
                e.printStackTrace()
                Log.d(ContentValues.TAG, "getAllAlbums:  error ")
            }
        }
        return albumsList
    }


    override suspend fun getUserPhotos(albumId: Int): List<Photos> {
        withContext(Dispatchers.Default) {
            try {
                val response = apiService.getUserPhotos(albumId).body() ?: listOf()
                photosList = response
                Log.d(ContentValues.TAG, "getAllAllPhotos: success ${photosList.toString()}")

            } catch (e: Exception) {
                e.printStackTrace()
                Log.d(ContentValues.TAG, "getAllAllPhotos:  error ")
            }
        }
        return photosList
    }
}