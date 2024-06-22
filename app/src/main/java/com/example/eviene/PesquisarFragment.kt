package com.example.eviene

import com.example.eviene.adapters.UserAdapter
import com.example.eviene.models.User
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
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
        val token = PreferencesManager.getToken(requireContext())
        RetrofitClient.getClient(token!!).searchProfiles(query).enqueue(object : Callback<List<User>> {
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
    private fun onUserClick(user: User) {
        replaceFragment(PerfilFragment.newInstance(user.username, false))
        Toast.makeText(requireContext(), "Clicked on: ${user.name}", Toast.LENGTH_SHORT).show()
        // Handle the click event, such as opening a new fragment or activity
    }
    fun replaceFragment(fragment : Fragment){
        val fragmentManager = parentFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.container, fragment)
        fragmentTransaction.commit()
    }
}
