package com.example.postfeed.domain.model

data class Post(
    val id: Int,
    val userId: Int,
    val title: String,
    val body: String
)