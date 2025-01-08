package com.example.postfeed.domain.usecase

import com.example.postfeed.domain.model.Post
import com.example.postfeed.domain.repository.PostRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class GetPostsUseCaseTest {

    private lateinit var postRepository: PostRepository
    private lateinit var getPostsUseCase: GetPostsUseCase

    @Before
    fun setup() {
        postRepository = mockk()
        getPostsUseCase = GetPostsUseCase(postRepository)
    }

    @Test
    fun `invoke should return list of posts when repository succeeds`() = runTest {
        // Arrange
        val mockPosts = listOf(
            Post(id = 1, userId = 1, title = "Title 1", body = "Body 1"),
            Post(id = 2, userId = 1, title = "Title 2", body = "Body 2")
        )
        coEvery { postRepository.getPosts() } returns mockPosts

        // Act
        val result = getPostsUseCase()

        // Assert
        assertEquals(mockPosts, result)
        coVerify { postRepository.getPosts() }
    }

    @Test
    fun `invoke should throw exception when repository fails`() = runTest {
        // Arrange
        val exception = RuntimeException("Error fetching posts")
        coEvery { postRepository.getPosts() } throws exception

        // Act & Assert
        try {
            getPostsUseCase()
            assertTrue("Exception was not thrown", false)
        } catch (e: Exception) {
            assertEquals("Error fetching posts", e.message)
        }
        coVerify { postRepository.getPosts() }
    }
}