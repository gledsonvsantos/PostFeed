package com.example.postfeed.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.postfeed.data.remote.FirebaseService
import com.example.postfeed.domain.model.Post
import com.example.postfeed.domain.usecase.GetPostsUseCase
import com.example.postfeed.utils.testing.getOrAwaitValue
import com.example.postfeed.utils.testing.rules.MainDispatcherRule
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class PostViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var getPostsUseCase: GetPostsUseCase
    private lateinit var firebaseService: FirebaseService
    private lateinit var viewModel: PostViewModel

    @Before
    fun setup() {
        getPostsUseCase = mockk()
        firebaseService = mockk(relaxed = true)
        viewModel = PostViewModel(getPostsUseCase, firebaseService)
    }

    @Test
    fun `fetchPosts should update posts LiveData on success`() = runTest {
        // Arrange
        val mockPosts = listOf(
            Post(id = 1, userId = 1, title = "Title 1", body = "Body 1"),
            Post(id = 2, userId = 1, title = "Title 2", body = "Body 2")
        )
        coEvery { getPostsUseCase() } returns mockPosts

        val observer = mockk<Observer<List<Post>>>(relaxed = true)
        viewModel.posts.observeForever(observer)

        // Act
        viewModel.fetchPosts()
        advanceUntilIdle() // Aguarda a conclusão das corrotinas

        // Assert
        assertEquals(mockPosts, viewModel.posts.getOrAwaitValue())
        coVerify { getPostsUseCase() }
        viewModel.posts.removeObserver(observer)
    }

    @Test
    fun `fetchPosts should update error LiveData on failure`() = runTest {
        // Arrange
        val exception = RuntimeException("Test error")
        coEvery { getPostsUseCase() } throws exception

        val observer = mockk<Observer<String>>(relaxed = true)
        viewModel.error.observeForever(observer)

        // Act
        viewModel.fetchPosts()
        advanceUntilIdle()

        // Assert
        assertEquals("Error fetching posts: Test error", viewModel.error.getOrAwaitValue())
        coVerify { firebaseService.reportCrash("Failed to fetch posts", exception) }
        viewModel.error.removeObserver(observer)
    }
}