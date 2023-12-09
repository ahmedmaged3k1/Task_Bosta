package com.example.bionic_time.domain.useCases

import com.example.bionic_time.data.dataSource.remoteDataSource.entities.Photos
import com.example.bionic_time.domain.repositories.RemoteRepository
import javax.inject.Inject

class PhotosUseCase @Inject constructor(private val remoteRepositoryImp: RemoteRepository) {
    suspend fun getAllAlbumsPhotos(albumId: Int) : List<Photos>?{
        return remoteRepositoryImp.getUserPhotos(albumId)
    }
}