package com.example.eviene

// File: ProfileActivity.kt
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.GridView
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.size
import androidx.fragment.app.Fragment
import com.example.eviene.adapters.ImageGridAdapter
import com.squareup.picasso.Picasso

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

        // Initialize views
        profileImage = view.findViewById(R.id.profile_image)
        username = view.findViewById(R.id.username)
        bio = view.findViewById(R.id.bio)
        postsCount = view.findViewById(R.id.posts_count)
        followersCount = view.findViewById(R.id.followers_count)
        followingCount = view.findViewById(R.id.following_count)
        editProfileButton = view.findViewById(R.id.edit_profile_button)
        gridView = view.findViewById(R.id.grid_view)

        // Carrega dados do perfil
        loadProfileData()

        // Carrega a gridview com as fotos
        setupGridView()

        return view
    }

    private fun loadProfileData() {
        // Placeholder for loading profile data, replace with actual data source
        profileImage.setImageResource(R.drawable.baseline_account_circle_24)
        username.text = "john_doe"
        bio.text = "Photographer & Traveler"
        followersCount.text = "1.2K"
        followingCount.text = "180"

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
        postsCount.text = adapter.count.toString()
    }
}
