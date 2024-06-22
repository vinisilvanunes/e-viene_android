package com.example.eviene

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.eviene.adapters.PostAdapter
import com.example.eviene.models.Post
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var postAdapter: PostAdapter
    private val posts = mutableListOf<Post>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        recyclerView = view.findViewById(R.id.listPosts)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        postAdapter = PostAdapter(posts)
        recyclerView.adapter = postAdapter

        fetchPosts()
        return view
    }

    private fun fetchPosts() {
        val token = PreferencesManager.getToken(requireContext())
        RetrofitClient.getClient(token!!).getPosts()
            .enqueue(object : Callback<List<Post>> {
                override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            postAdapter = PostAdapter(it)
                            recyclerView.adapter = postAdapter
                        }
                    } else {
                        Log.e("PostsFragment", "Error: ${response.errorBody()?.string()}")
                    }
                }

                override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                    Log.e("PostsFragment", "Error fetching posts: ${t.message}")
                }
            })
    }

}