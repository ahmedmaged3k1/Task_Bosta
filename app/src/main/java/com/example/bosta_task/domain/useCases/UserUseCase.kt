package com.example.bosta_task.domain.useCases

import com.example.bosta_task.data.dataSource.remoteDataSource.entities.User
import com.example.bosta_task.domain.repositories.RemoteRepository


class UserUseCase (private val remoteRepositoryImp: RemoteRepository) {
    suspend fun getAllUsers() : List<User>{
        return remoteRepositoryImp.getUsers()
    }
}