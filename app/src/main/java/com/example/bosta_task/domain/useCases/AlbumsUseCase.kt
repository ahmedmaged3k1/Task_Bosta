package com.example.bionic_time.domain.useCases

import com.example.bionic_time.data.dataSource.remoteDataSource.entities.Albums
import com.example.bosta_task.domain.repositories.RemoteRepository
import javax.inject.Inject

class AlbumsUseCase @Inject constructor(private val remoteRepositoryImp: RemoteRepository) {
    suspend fun getAllUserAlbums(userId: Int) : List<Albums>?{
        return remoteRepositoryImp.getUserAlbum(userId)
    }
}