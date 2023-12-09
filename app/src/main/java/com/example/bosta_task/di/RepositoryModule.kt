package com.example.bionic_time.di

import com.example.bionic_time.data.dataSource.remoteDataSource.RemoteRepositoryImp
import com.example.bionic_time.domain.repositories.RemoteRepository
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