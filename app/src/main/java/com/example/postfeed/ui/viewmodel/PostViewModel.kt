package com.example.postfeed.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.postfeed.data.remote.FirebaseService
import com.example.postfeed.domain.model.Post
import com.example.postfeed.domain.usecase.GetPostsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(private val getPostsUseCase: GetPostsUseCase) : ViewModel() {

    private val _posts = MutableLiveData<List<Post>>()
    val posts: LiveData<List<Post>> = _posts

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun fetchPosts() {
        viewModelScope.launch {
            try {
                _posts.value = getPostsUseCase()
            } catch (e: Exception) {
                FirebaseService.reportCrash("Failed to fetch posts", e)
                _error.value = "Error fetching posts: ${e.message}"
            }
        }
    }
}