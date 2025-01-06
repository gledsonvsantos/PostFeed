package com.example.postfeed.domain.usecase

import com.example.postfeed.domain.model.Post
import com.example.postfeed.domain.repository.PostRepository

class GetPostsUseCase(private val repository: PostRepository) {
    suspend operator fun invoke(): List<Post> {
        return repository.getPosts()
    }
}