package com.example.postfeed.data.remote

import com.example.postfeed.data.model.PostDto
import retrofit2.http.GET

interface ApiService {

    @GET("posts")
    suspend fun getPosts(): List<PostDto>
}