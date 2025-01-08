package com.example.postfeed.di

import com.example.postfeed.data.remote.ApiService
import com.example.postfeed.data.remote.FirebaseService
import com.example.postfeed.data.remote.RetrofitInstance
import com.example.postfeed.data.repository.PostRepositoryImpl
import com.example.postfeed.domain.repository.PostRepository
import com.example.postfeed.domain.usecase.GetPostsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideApiService(): ApiService {
        return RetrofitInstance.api
    }

    @Provides
    fun providePostRepository(apiService: ApiService): PostRepository {
        return PostRepositoryImpl(apiService)
    }

    @Provides
    fun provideGetPostsUseCase(postRepository: PostRepository): GetPostsUseCase {
        return GetPostsUseCase(postRepository)
    }

    @Provides
    @Singleton
    fun provideFirebaseService(): FirebaseService {
        return FirebaseService()
    }

}