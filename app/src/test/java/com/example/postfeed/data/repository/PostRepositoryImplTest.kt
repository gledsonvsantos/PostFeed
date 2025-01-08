package com.example.postfeed.data.repository

import com.example.postfeed.data.model.PostDto
import com.example.postfeed.data.remote.ApiService
import com.example.postfeed.domain.model.Post
import com.example.postfeed.domain.repository.PostRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class PostRepositoryImplTest {

    private val apiService: ApiService = mockk()
    private val repository: PostRepository = PostRepositoryImpl(apiService)

    @Test
    fun `getPosts should return domain list when ApiService succeeds`() = runTest {
        // Arrange
        val mockDtos = listOf(
            PostDto(id = 1, userId = 1, title = "Title 1", body = "Body 1"),
            PostDto(id = 2, userId = 1, title = "Title 2", body = "Body 2")
        )
        coEvery { apiService.getPosts() } returns mockDtos

        val expectedPosts = mockDtos.map { Post(it.id, it.userId, it.title, it.body) }

        // Act
        val result = repository.getPosts()

        // Assert
        assertEquals(expectedPosts, result)
        coVerify { apiService.getPosts() }
    }

    @Test
    fun `getPosts should throw exception when ApiService fails`() = runTest {
        // Arrange
        val exception = RuntimeException("Error fetching posts")
        coEvery { apiService.getPosts() } throws exception

        // Act & Assert
        try {
            repository.getPosts()
            assert(false) { "Expected exception to be thrown" }
        } catch (e: Exception) {
            assertEquals("Error fetching posts", e.message)
        }
        coVerify { apiService.getPosts() }
    }
}