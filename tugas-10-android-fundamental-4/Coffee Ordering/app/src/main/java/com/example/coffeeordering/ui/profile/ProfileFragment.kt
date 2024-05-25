package com.example.coffeeordering.ui.profile

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.fragment.findNavController
import com.example.coffeeordering.R
import com.example.coffeeordering.databinding.FragmentProfileBinding
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

class ProfileFragment : Fragment(), View.OnClickListener {
    private lateinit var mUserPreference: UserPreference
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private val fileName = "user_image.png"
    private var isPreferenceEmpty = false
    private lateinit var userModel: UserModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)

        binding.btnShare.setOnClickListener(this)
        binding.btnSave.setOnClickListener(this)
        binding.imgProfile.setOnClickListener(this)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mUserPreference = UserPreference(requireContext())

        showExistingPreference()
    }

    private fun showExistingPreference() {
        userModel = mUserPreference.getUser()
        populateView(userModel)
        checkForm(userModel)
    }

    private fun populateView(userModel: UserModel) {
        binding.fullName.text =
            if (userModel.fullName.toString().isEmpty()) "Tidak Ada" else userModel.fullName
        binding.nim.text =
            if (userModel.nim.toString().isEmpty()) "Tidak Ada" else userModel.nim
        binding.prodi.text =
            if (userModel.prodi.toString().isEmpty()) "Tidak Ada" else userModel.prodi
        binding.fakultas.text =
            if (userModel.fakultas.toString().isEmpty()) "Tidak Ada" else userModel.fakultas
        binding.universitas.text =
            if (userModel.universitas.toString().isEmpty()) "Tidak Ada" else userModel.universitas
        binding.email.text =
            if (userModel.email.toString().isEmpty()) "Tidak Ada" else userModel.email
        val bitmap = loadImageFromInternalStorage()
        if (bitmap != null) {
            binding.imgProfile.setImageBitmap(bitmap)
        }
    }

    private fun checkForm(userModel: UserModel) {
        when {
            userModel.fullName.toString().isNotEmpty() -> {
                binding.btnSave.text = getString(R.string.change)
                isPreferenceEmpty = false
            }
            else -> {
                binding.btnSave.text = getString(R.string.save)
                isPreferenceEmpty = true
            }
        }
    }

    private val pickImageLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let {
            val bitmap = BitmapFactory.decodeStream(requireContext().contentResolver.openInputStream(it))
            if (bitmap != null) {
                binding.imgProfile.setImageBitmap(bitmap)
            }
            saveImageToInternalStorage(bitmap)
        }
    }

    private fun openGallery() {
        pickImageLauncher.launch("image/*")
    }

    private fun saveImageToInternalStorage(bitmap: Bitmap) {
        val file = File(requireContext().filesDir, fileName)
        val outputStream = FileOutputStream(file)
        try {
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
        } finally {
            outputStream.close()
        }
    }

    private fun loadImageFromInternalStorage(): Bitmap? {
        val file = File(requireContext().filesDir, fileName)
        return if (file.exists()) {
            val inputStream = FileInputStream(file)
            BitmapFactory.decodeStream(inputStream)
        } else {
            null
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_share -> {
                Log.d("Profile Fragment", "btn share clicked")
                val dataIntent = "${userModel.nim} \n ${userModel.fullName} \n ${userModel.prodi} \n ${userModel.fakultas} \n ${userModel.universitas}"

                val shareIntent = Intent(Intent.ACTION_SEND).apply {
                    type = "text/plain"
                    putExtra(Intent.EXTRA_EMAIL, arrayOf("haridaramadhan@gmail.com"))
                    putExtra(Intent.EXTRA_SUBJECT, "pengen kenalan")
                    putExtra(Intent.EXTRA_TEXT, dataIntent)
                }
                startActivity(Intent.createChooser(shareIntent, "Share via..."))
            }

            R.id.btn_save -> {
                val bundle = Bundle().apply {
                    putParcelable("USER", userModel)
                    putInt(FormUserPreferenceFragment.EXTRA_TYPE_FORM, if (isPreferenceEmpty) FormUserPreferenceFragment.TYPE_ADD else FormUserPreferenceFragment.TYPE_EDIT)
                }

                findNavController().navigate(R.id.action_profileFragment_to_userPreferenceFragment, bundle)
            }

            R.id.img_profile -> {
                openGallery()
            }
        }
    }
}