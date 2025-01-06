package com.example.postfeed.domain.repository

import com.example.postfeed.domain.model.Post

interface PostRepository {
    suspend fun getPosts(): List<Post>
}