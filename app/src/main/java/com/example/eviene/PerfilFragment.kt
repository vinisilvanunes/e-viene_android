package com.example.eviene

import android.os.Bundle
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
import com.squareup.picasso.Picasso
import kotlinx.coroutines.launch

class PerfilFragment : Fragment() {

    private lateinit var profileImage: ImageView
    private lateinit var username: TextView
    private lateinit var bio: TextView
    private lateinit var postsCount: TextView
    private lateinit var followersCount: TextView
    private lateinit var followingCount: TextView
    private lateinit var editProfileButton: Button
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
        editProfileButton = view.findViewById(R.id.edit_profile_button)
        gridView = view.findViewById(R.id.grid_view)

        val userId = "viniinunes"
        val token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjY2NjBlOTQ1MzgyM2Q3MDQyY2RlNjhlMiIsImlhdCI6MTcxNzYzMDIxNn0._qZRFTk-oOGRzAAoqmiCYoDTWITEfLx3h0sDcr8gz1U"
        val user = getUserData(userId, token)

        setupGridView()

        return view
    }

    private fun getUserData(userId: String, token: String) {
        lifecycleScope.launch {
            try {
                val user = RetrofitClient.getClient(token).getUser(userId)
                loadProfileData(user[0])
            } catch (e: Exception) {
                // Handle exceptions
            }
        }
    }

    private fun loadProfileData(user: User) {
        // Carrega as informações do perfil. Substituir com os dados reais da API

        //val profileImageUrl = "https://img.freepik.com/fotos-gratis/garota-feliz-sorridente-faz-desejo-dedos-cruzados-esperancosos-desejando-boa-sorte-olhos-fechados-com-expressao-de-rosto-animado-de-pe-sobre-fundo-branco_176420-45410.jpg" // Replace with actual URL

        Log.d("ProfileFragment", "User data: $user")
        username.text = user.username
        bio.text = user.bio
        followersCount.text = user.followers.toString()
        followingCount.text = user.following.toString()
        postsCount.text = user.posts.toString()

        Picasso.get()
            .load(user.profilePicture)
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
