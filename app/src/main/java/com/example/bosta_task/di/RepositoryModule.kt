package com.example.bosta_task.di

import com.example.bosta_task.data.dataSource.remoteDataSource.RemoteRepositoryImp
import com.example.bosta_task.domain.repositories.RemoteRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindMyProductsRepository(
        myRepositoryImpl: RemoteRepositoryImp
    ): RemoteRepository
}