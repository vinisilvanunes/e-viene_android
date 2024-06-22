package com.example.eviene

import android.content.Context
import android.os.Bundle
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.GridView
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.size
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import com.example.eviene.adapters.ImageGridAdapter
import com.example.eviene.models.User
import com.example.eviene.models.UserInfos
import com.squareup.picasso.Picasso
import kotlinx.coroutines.launch

class PerfilFragment : Fragment() {
    companion object {
        private const val ARG_USER = "userId"
        private const val ARG_IS_CURRENT_USER = "isCurrentUser"
        fun newInstance(userId: String?, isCurrentUser: Boolean): PerfilFragment {
            val fragment = PerfilFragment()
            val args = Bundle()
            args.putString(ARG_USER, userId)
            args.putBoolean(ARG_IS_CURRENT_USER, isCurrentUser)
            fragment.arguments = args
            Log.e("CHEGUEI NO COMPANION", "OBJECT")
            return fragment
        }
    }

    private lateinit var profileImage: ImageView
    private lateinit var username: TextView
    private lateinit var bio: TextView
    private lateinit var postsCount: TextView
    private lateinit var followersCount: TextView
    private lateinit var followingCount: TextView
    private lateinit var profileButton: Button
    private lateinit var gridView: GridView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_perfil, container, false)

        // inicializa as views
        profileImage = view.findViewById(R.id.profile_image)
        username = view.findViewById(R.id.username)
        bio = view.findViewById(R.id.bio)
        postsCount = view.findViewById(R.id.posts_count)
        followersCount = view.findViewById(R.id.followers_count)
        followingCount = view.findViewById(R.id.following_count)
        profileButton = view.findViewById(R.id.follow_edit_profile)
        gridView = view.findViewById(R.id.grid_view)
        profileButton.visibility = View.VISIBLE
        val token = PreferencesManager.getToken(requireContext())
        val userId  = arguments?.getString(ARG_USER)
        val isCurrentUser= arguments?.getBoolean(ARG_IS_CURRENT_USER)
        getUserData(userId, token!!, isCurrentUser!!)

        profileButton = view.findViewById(R.id.follow_edit_profile)
        profileButton.setOnClickListener {
            if (profileButton.text=="Editar"){
                replaceFragment(EditProfileFragment())
            } else {
                lifecycleScope.launch {
                    try{
                        RetrofitClient.getClient(token).followUser(userId.toString())
                    } catch (e: Exception) {
                        //tratar erro
                    }
                }
                if (profileButton.text=="Seguir"){
                    profileButton.text = "Seguindo"
                } else {
                    profileButton.text = "Seguir"
                }
            }

        }

        return view
    }

    private fun getUserData(username: String?, token: String, isCurrentUser: Boolean){
        lifecycleScope.launch {
            try {
                if (isCurrentUser){
                    val user = RetrofitClient.getClient(token).getLoggedUser()
                    profileButton.text = "Editar"
                    loadProfileData(user)
                } else {
                    val user = RetrofitClient.getClient(token).getUser(username!!)
                    if(user.followers.contains(username)){
                        profileButton.text = "Seguindo"
                    } else {
                        profileButton.text = "Seguir"
                    }
                    loadProfileData(user)
                }

            } catch (e: Exception) {
                Log.e("erro", e.message.toString())
                profileButton.visibility = View.GONE
                // Handle exceptions
            }
        }
    }

    private fun loadProfileData(user: UserInfos) {
        // Carrega as informações do perfil. Substituir com os dados reais da API

        //val profileImageUrl = "https://img.freepik.com/fotos-gratis/garota-feliz-sorridente-faz-desejo-dedos-cruzados-esperancosos-desejando-boa-sorte-olhos-fechados-com-expressao-de-rosto-animado-de-pe-sobre-fundo-branco_176420-45410.jpg" // Replace with actual URL
        username.text = user.username
        bio.text = user.bio
        followersCount.text = user.followers
        followingCount.text = user.following
        postsCount.text = user.posts.count().toString()
        try {
            Picasso.get()
                .load(user.profilePic)
                .placeholder(R.drawable.baseline_account_circle_24) // Imagem que aparecerá enquanto carrega
                .error(R.drawable.baseline_account_circle_24) // Caso haja algum erro, essa imagem aparecerá
                .transform(CircleTransform()) // Transforma em imagem circular
                .into(profileImage)

            setupGridView(user)
        } catch (e: Exception){

        }
    }

    private fun setupGridView(user: UserInfos) {
        val imageUrls  = user.posts.map { it.images }
        val imgsStrings: List<String> = imageUrls.flatMap { it }
        Log.e("lista imagens", imgsStrings.toString())
        val adapter = ImageGridAdapter(requireContext(), imgsStrings)
        gridView.adapter = adapter
    }

    fun replaceFragment(fragment : Fragment){
        val fragmentManager = parentFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.container, fragment)
        fragmentTransaction.commit()
    }
}
