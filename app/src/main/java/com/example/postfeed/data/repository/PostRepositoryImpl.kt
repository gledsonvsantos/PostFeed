package com.example.postfeed.data.repository

import com.example.postfeed.data.remote.ApiService
import com.example.postfeed.domain.model.Post
import com.example.postfeed.domain.repository.PostRepository

class PostRepositoryImpl(private val apiService: ApiService) : PostRepository {
    override suspend fun getPosts(): List<Post> {
        return apiService.getPosts().map { it.toDomain() }
    }
}