package com.example.postfeed.ui.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.postfeed.data.remote.FirebaseService
import com.example.postfeed.databinding.FragmentPostBinding
import com.example.postfeed.domain.model.Post
import com.example.postfeed.ui.listeners.PostClickListener
import com.example.postfeed.ui.adapters.PostAdapter
import com.example.postfeed.ui.viewmodel.PostViewModel
import com.google.firebase.analytics.FirebaseAnalytics
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PostFragment : Fragment(), PostClickListener {

    @Inject
    lateinit var firebaseService: FirebaseService

    private var _binding: FragmentPostBinding? = null
    private val binding get() = _binding!!
    private val viewModel: PostViewModel by viewModels()
    private lateinit var firebaseAnalytics: FirebaseAnalytics
    private lateinit var postAdapter: PostAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPostBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize Firebase Analytics
        firebaseAnalytics = FirebaseAnalytics.getInstance(requireContext())

        setupRecyclerView()
        setupObservers()
        setupCrashTestButton()

        // Fetch posts
        binding.progressBar.visibility = View.VISIBLE
        viewModel.fetchPosts()
    }

    private fun setupRecyclerView() {
        postAdapter = PostAdapter(this)
        binding.recyclerViewPosts.apply {
            adapter = postAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun setupObservers() {
        viewModel.posts.observe(viewLifecycleOwner) { posts ->
            binding.progressBar.visibility = View.GONE
            if (posts.isNullOrEmpty()) {
                Log.d("PostFragment", "No posts received")
            } else {
                postAdapter.submitList(posts)
            }
        }

        viewModel.error.observe(viewLifecycleOwner) { errorMessage ->
            errorMessage?.let {
                Log.e("PostFragment", "Error: $it")
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupCrashTestButton() {
        binding.btnTestCrash.setOnClickListener {
            throw RuntimeException("Crash Test")
        }
    }

    override fun onPostClicked(post: Post) {
        Log.d("PostFragment", "Post clicked: ${post.title}")
        firebaseService.logCustomEvent(firebaseAnalytics, "post_clicked", Bundle().apply {
            putInt("post_id", post.id)
            putString("post_title", post.title)
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}