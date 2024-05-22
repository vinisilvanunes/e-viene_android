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

        // Load profile data
        loadProfileData()

        // Set up the GridView
        setupGridView()

        return view
    }

    private fun loadProfileData() {
        // Placeholder for loading profile data, replace with actual data source
        profileImage.setImageResource(R.drawable.baseline_account_circle_24)
        username.text = "john_doe"
        bio.text = "Photographer & Traveler"
        postsCount.text = "150"
        followersCount.text = "1.2K"
        followingCount.text = "180"

    }

    private fun setupGridView() {
        val imageUrls = listOf(
            "https://s2-techtudo.glbimg.com/SSAPhiaAy_zLTOu3Tr3ZKu2H5vg=/0x0:1024x609/888x0/smart/filters:strip_icc()/i.s3.glbimg.com/v1/AUTH_08fbf48bc0524877943fe86e43087e7a/internal_photos/bs/2022/c/u/15eppqSmeTdHkoAKM0Uw/dall-e-2.jpg",
            "https://files.tecnoblog.net/wp-content/uploads/2022/09/stable-diffusion-imagem.jpg",
            "https://www.pontotel.com.br/wp-content/uploads/2022/05/imagem-corporativa.jpg"
            // Add more image URLs
        )

        val adapter = ImageGridAdapter(requireContext(), imageUrls)
        gridView.adapter = adapter
    }
}


//
//import android.os.Bundle
//import androidx.fragment.app.Fragment
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//
//// TODO: Rename parameter arguments, choose names that match
//// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//private const val ARG_PARAM1 = "param1"
//private const val ARG_PARAM2 = "param2"
//
///**
// * A simple [Fragment] subclass.
// * Use the [PerfilFragment.newInstance] factory method to
// * create an instance of this fragment.
// */
//class PerfilFragment : Fragment() {
//    // TODO: Rename and change types of parameters
//    private var param1: String? = null
//    private var param2: String? = null
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        arguments?.let {
//            param1 = it.getString(ARG_PARAM1)
//            param2 = it.getString(ARG_PARAM2)
//        }
//    }
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_perfil, container, false)
//    }
//
//    companion object {
//        /**
//         * Use this factory method to create a new instance of
//         * this fragment using the provided parameters.
//         *
//         * @param param1 Parameter 1.
//         * @param param2 Parameter 2.
//         * @return A new instance of fragment PerfilFragment.
//         */
//        // TODO: Rename and change types and number of parameters
//        @JvmStatic
//        fun newInstance(param1: String, param2: String) =
//            PerfilFragment().apply {
//                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
//                }
//            }
//    }
//} */