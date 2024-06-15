package com.example.eviene

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.ByteArrayOutputStream

class PostarFragment : Fragment() {

    private lateinit var txtTexto: EditText
    private lateinit var addImage: ImageView
    private lateinit var btnEnviar: Button
    private var imageUri: Uri? = null

    private val pickImageLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        if (uri != null) {
            imageUri = uri
            addImage.setImageURI(uri)
            addImage.visibility = View.VISIBLE
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_postar,container,false)

        txtTexto = view.findViewById(R.id.txtTexto)
        addImage = view.findViewById(R.id.addImage)
        btnEnviar = view.findViewById(R.id.btnEnviar)

        addImage.setOnClickListener {
            openGallery()
        }

        btnEnviar.setOnClickListener {
            createPost()
        }

        return view
    }

    private fun openGallery() {
        pickImageLauncher.launch("image/*")
    }

    private fun createPost() {
        val token = PreferencesManager.getToken(requireContext())
        if (token == null) {
            Toast.makeText(requireContext(), "User not logged in", Toast.LENGTH_SHORT).show()
            return
        }

        val text = txtTexto.text.toString()
        val textRequestBody = if (text.isNotBlank()) {
            RequestBody.create("text/plain".toMediaTypeOrNull(), text)
        } else {
            null
        }

        val imagePart = imageUri?.let { uri ->
            val bitmap = MediaStore.Images.Media.getBitmap(requireActivity().contentResolver, uri)
            val stream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
            val byteArray = stream.toByteArray()
            val requestBody = RequestBody.create("image/jpeg".toMediaTypeOrNull(), byteArray)
            MultipartBody.Part.createFormData("image", "image.jpg", requestBody)
        }

        val apiService = RetrofitClient.instance
        val call = apiService.createPost("Bearer $token", textRequestBody, imagePart)

        call.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    Toast.makeText(requireContext(), "Post created successfully", Toast.LENGTH_SHORT).show()
                    // Limpar campos após criar o post
                    txtTexto.text.clear()
                    addImage.setImageURI(null)
                    addImage.visibility = View.GONE
                    imageUri = null
                } else {
                    Toast.makeText(requireContext(), "Error creating post", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Toast.makeText(requireContext(), "Error: " + t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }
}