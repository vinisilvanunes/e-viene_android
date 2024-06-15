package com.example.eviene

import android.os.Bundle
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.GridView
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.size
import androidx.fragment.app.Fragment
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
        fun newInstance(userId: String, isCurrentUser: Boolean): PerfilFragment {
            val fragment = PerfilFragment()
            val args = Bundle()
            args.putString(ARG_USER, userId)
            args.putBoolean(ARG_IS_CURRENT_USER, isCurrentUser)
            fragment.arguments = args
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
        val userId = arguments?.getString(ARG_USER)
        val isCurrentUser= arguments?.getBoolean(ARG_IS_CURRENT_USER)
        getUserData(userId!!, token!!, isCurrentUser!!)
        setupGridView()
        return view
    }

    private fun getUserData(username: String, token: String, isCurrentUser: Boolean){
        lifecycleScope.launch {
            try {
                if (isCurrentUser){
                    val user = RetrofitClient.getClient(token).getLoggedUser()
                    profileButton.text = "Editar"
                    loadProfileData(user)
                } else {
                    val user = RetrofitClient.getClient(token).getUser(username)
                    if(user.followers.contains(username)){
                        profileButton.text = "Seguindo"
                    } else {
                        profileButton.text = "Seguir"
                    }
                    loadProfileData(user)
                }

            } catch (e: Exception) {
                profileButton.visibility = View.GONE
                // Handle exceptions
            }
        }
    }

    private fun getLoggedUserData(token: String) {
        lifecycleScope.launch {
            try {
                val user = RetrofitClient.getClient(token).getLoggedUser()
            } catch (e: Exception) {
                // Handle exceptions
            }
        }
    }

    private fun loadProfileData(user: UserInfos) {
        // Carrega as informações do perfil. Substituir com os dados reais da API

        //val profileImageUrl = "https://img.freepik.com/fotos-gratis/garota-feliz-sorridente-faz-desejo-dedos-cruzados-esperancosos-desejando-boa-sorte-olhos-fechados-com-expressao-de-rosto-animado-de-pe-sobre-fundo-branco_176420-45410.jpg" // Replace with actual URL

        username.text = user.username
        bio.text = "user.bio"
        followersCount.text = user.followers.toString()
        followingCount.text = user.following.toString()
        postsCount.text = user.posts.toString()

        Picasso.get()
            .load(user.profilePic)
            .placeholder(R.drawable.baseline_account_circle_24) // Imagem que aparecerá enquanto carrega
            .error(R.drawable.baseline_account_circle_24) // Caso haja algum erro, essa imagem aparecerá
            .transform(CircleTransform()) // Transforma em imagem circular
            .into(profileImage)

    }

    private fun setupGridView() {
        val imageUrls = listOf(
            "https://www.consultoriarr.com.br/wp-content/uploads/2022/03/img-como-abrir-uma-produtora-de-eventos-passo-a-passo-completo-em-2022.jpg",
            "https://www.lopes.com.br/blog/wp-content/uploads/2017/03/evento-no-rio.jpg",
            "https://classic.exame.com/wp-content/uploads/2020/07/Lazer-no-Parque-do-Ibirapuera-ap%C3%B3s-a-flexibiliza%C3%A7%C3%A3o-do-isolamento-social-durante-a-pandemia-de-covid-19.jpg?quality=70&strip=info&w=1024",
            "https://p2.trrsf.com/image/fget/cf/774/0/images.terra.com/2023/10/21/1303692945-afz6wclpbnhynkeeqgkk7o5gqq.png",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQfpeylwEIGi59uVWzHivljHs0sH2WO2LwcQA9OS24viA&s",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRba9z6swplIKflAOhHNUNyi4pwbZpnjOfNw8lP6wdVtw&s",
            "https://static.todamateria.com.br/upload/sa/mb/samba-de-roda-og.jpg",
            "https://veja.abril.com.br/wp-content/uploads/2022/09/IHF_RIR22_Dia09-Publico-ArielMartini-0054.jpg?quality=70&strip=info&w=720&crop=1"
        )

        val adapter = ImageGridAdapter(requireContext(), imageUrls)
        gridView.adapter = adapter
    }
}
