package com.example.bosta_task.di

import com.example.bosta_task.data.dataSource.remoteDataSource.ApiService
import com.example.bionic_time.data.network.Credentials
import com.example.bosta_task.domain.repositories.RemoteRepository
import com.example.bionic_time.domain.useCases.AlbumsUseCase
import com.example.bionic_time.domain.useCases.PhotosUseCase
import com.example.bosta_task.domain.useCases.UserUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideUserUseCase(remoteRepository: RemoteRepository, ): UserUseCase {
        return UserUseCase(remoteRepository)
    }
    @Provides
    @Singleton
    fun provideAlbumsUseCase(remoteRepository: RemoteRepository, ): AlbumsUseCase {
        return AlbumsUseCase(remoteRepository)
    }
    @Provides
    @Singleton
    fun providePhotosUseCase(remoteRepository: RemoteRepository, ): PhotosUseCase {
        return PhotosUseCase(remoteRepository)
    }
    @Singleton
    @Provides
    fun provideRetrofitInstance(
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Credentials.baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

}