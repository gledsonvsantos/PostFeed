package com.example.postfeed.data.model

import com.example.postfeed.domain.model.Post

data class PostDto(
    val id: Int,
    val userId: Int,
    val title: String,
    val body: String
) {
    fun toDomain(): Post {
        return Post(id, userId, title, body)
    }
}