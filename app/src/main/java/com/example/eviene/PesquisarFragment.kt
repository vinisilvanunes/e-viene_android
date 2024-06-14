package com.example.eviene

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.eviene.adapters.UserAdapter
import com.example.eviene.models.User

class PesquisarFragment : Fragment() {

    private lateinit var searchInput: EditText
    private lateinit var recyclerView: RecyclerView
    private lateinit var userAdapter: UserAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_pesquisar, container, false)

        searchInput = view.findViewById(R.id.search_input)
        recyclerView = view.findViewById(R.id.recycler_view)

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        userAdapter = UserAdapter(emptyList())
        recyclerView.adapter = userAdapter

        searchInput.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val query = s.toString()
                if (query.isNotEmpty()) {
                    searchProfiles(query)
                } else {
                    userAdapter.updateUsers(emptyList())
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        // For testing without API, populate the RecyclerView with mock data
        userAdapter.updateUsers(generateMockUsers())

        return view
    }

    private fun searchProfiles(query: String) {
        // Mock search implementation
        val allUsers = generateMockUsers()
        val filteredUsers = allUsers.filter {
            it.name.contains(query, ignoreCase = true) || it.username.contains(query, ignoreCase = true)
        }
        userAdapter.updateUsers(filteredUsers)
    }

    private fun generateMockUsers(): List<User> {
        return listOf(
            User(
                id = "1",
                name = "John Doe",
                username = "johndoe",
                email = "johndoe@example.com",
                birthdate = "",
                password = "",
                profilePicture = "https://pm1.aminoapps.com/7454/0ca8e2c45308a090cc7ee25c1ab50618eb89cf62r1-700-990v2_hq.jpg",
                bio = "Just a test user.",
                posts = emptyList(),
                eventAttended = emptyList(),
                followers = emptyList(),
                following = emptyList(),
                active = true
            ),
            User(
                id = "2",
                name = "Jane Smith",
                username = "janesmith",
                email = "janesmith@example.com",
                birthdate = "",
                password = "",
                profilePicture = "https://pm1.aminoapps.com/7497/0d8c001aac9805ec5f776fe825cc93ae0c73d52cr1-682-384v2_hq.jpg",
                bio = "Another test user.",
                posts = emptyList(),
                eventAttended = emptyList(),
                followers = emptyList(),
                following = emptyList(),
                active = true
            ),
            // Add more mock users as needed
        )
    }
}


/*
import com.example.eviene.adapters.UserAdapter
import com.example.eviene.models.User

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PesquisarFragment : Fragment() {

    private lateinit var searchInput: EditText
    private lateinit var recyclerView: RecyclerView
    private lateinit var userAdapter: UserAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_pesquisar, container, false)

        searchInput = view.findViewById(R.id.search_input)
        recyclerView = view.findViewById(R.id.recycler_view)

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        userAdapter = UserAdapter(emptyList())
        recyclerView.adapter = userAdapter

        searchInput.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val query = s.toString()
                if (query.isNotEmpty()) {
                    searchProfiles(query)
                } else {
                    userAdapter.updateUsers(emptyList())
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        return view
    }

    private fun searchProfiles(query: String) {
        RetrofitClient.instance.searchProfiles(query).enqueue(object : Callback<List<User>> {
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                if (response.isSuccessful) {
                    response.body()?.let { users ->
                        userAdapter.updateUsers(users)
                    }
                } else {
                    Toast.makeText(requireContext(), "Falha ao buscar usuários", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                Toast.makeText(requireContext(), "Ocorreu um erro: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
*/