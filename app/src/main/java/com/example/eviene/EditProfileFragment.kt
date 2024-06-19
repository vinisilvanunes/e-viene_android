package com.example.eviene

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Binder
import android.os.Bundle
import android.util.Log
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class EditProfileFragment : Fragment() {

    private lateinit var editProfileImage: ImageButton
    private lateinit var currentUsername: TextView
    private lateinit var txtName: EditText
    private lateinit var txtUsername: EditText
    private lateinit var txtBio: EditText
    private lateinit var txtBirthdate: EditText
    private lateinit var btnSave: Button
    private lateinit var lblDeleteAccount: TextView
    private var selectedImageUri: Uri? = null
    private lateinit var pickImageLauncher: ActivityResultLauncher<Intent>

    private val REQUEST_CODE_PERMISSIONS = 101

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_edit_profile, container, false)

        editProfileImage = view.findViewById(R.id.img_edit_image)
        currentUsername = view.findViewById(R.id.lbl_edit_username)
        txtName = view.findViewById(R.id.txt_edit_name)
        txtUsername = view.findViewById(R.id.txt_edit_username)
        txtBio = view.findViewById(R.id.txt_edit_bio)
        txtBirthdate = view.findViewById(R.id.txt_edit_birthdate)
        btnSave = view.findViewById(R.id.btn_edit_save)
        lblDeleteAccount = view.findViewById(R.id.lbl_delete_account)

        pickImageLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK && result.data != null) {
                selectedImageUri = result.data?.data
                editProfileImage.setImageURI(selectedImageUri)
            }
        }

        editProfileImage.setOnClickListener {
            Log.e("esssssssssssssssss","assssssssssssssssss")
            openImagePicker()
        }



        btnSave.setOnClickListener {
            updateUser()
        }
        Toast.makeText(requireContext(), "Profile saved", Toast.LENGTH_SHORT).show()

        lblDeleteAccount.setOnClickListener {
            // Implement account deletion logic here
            Toast.makeText(requireContext(), "Account deleted", Toast.LENGTH_SHORT).show()
        }

        return view
    }

    private fun openImagePicker() {
        checkPermissions()
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        pickImageLauncher.launch(intent)
    }


    private fun updateUser() {
        val apiService = RetrofitClient.getClient(PreferencesManager.getToken(requireContext())!!)
        val username =
            RequestBody.create("text/plain".toMediaTypeOrNull(), txtUsername.text.toString())
        val name = RequestBody.create("text/plain".toMediaTypeOrNull(), txtName.text.toString())
        val bio = RequestBody.create("text/plain".toMediaTypeOrNull(), txtBio.text.toString())
        val birthDate =
            RequestBody.create("text/plain".toMediaTypeOrNull(), txtBirthdate.text.toString())

        var profilePicPart: MultipartBody.Part? = null
        selectedImageUri?.let {
            val file = File(FileUtils.getPath(requireContext(), it)!!)
            val requestFile = RequestBody.create("image/*".toMediaTypeOrNull(), file)
            profilePicPart = MultipartBody.Part.createFormData("profilePic", file.name, requestFile)
        }

        apiService.updateUser(username, name, bio, birthDate, profilePicPart).enqueue(object :
            Callback<UpdateUserResponse> {
            override fun onResponse(
                call: Call<UpdateUserResponse>,
                response: Response<UpdateUserResponse>
            ) {
                if (response.isSuccessful) {
                    Toast.makeText(
                        requireContext(),
                        "Dados atualizados com sucesso",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Toast.makeText(
                        requireContext(),
                        response.message() ?: "Erro ao atualizar informações do usuário",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<UpdateUserResponse>, t: Throwable) {
                Toast.makeText(
                    requireContext(),
                    "Erro ao atualizar informações do usuário: ${t.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }


    private fun checkPermissions() {
        val permissions = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                permissions[0]
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                permissions,
                REQUEST_CODE_PERMISSIONS
            )
        }
    }
}