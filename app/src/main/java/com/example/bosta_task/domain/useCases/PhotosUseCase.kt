package com.example.bosta_task.domain.useCases

import com.example.bosta_task.data.dataSource.remoteDataSource.entities.Photos
import com.example.bosta_task.domain.repositories.RemoteRepository
import javax.inject.Inject

class PhotosUseCase @Inject constructor(private val remoteRepositoryImp: RemoteRepository) {
    suspend fun getAllAlbumsPhotos(albumId: Int) : List<Photos>{
        return remoteRepositoryImp.getUserPhotos(albumId)
    }
}