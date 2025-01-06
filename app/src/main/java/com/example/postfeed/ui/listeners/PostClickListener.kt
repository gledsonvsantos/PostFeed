package com.example.postfeed.ui.listeners

import com.example.postfeed.domain.model.Post

interface PostClickListener {
    fun onPostClicked(post: Post)
}