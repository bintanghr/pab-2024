package com.example.coffeeordering.ui.profile

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.coffeeordering.R
import com.example.coffeeordering.databinding.FragmentFormUserPreferenceBinding

class FormUserPreferenceFragment : Fragment(), View.OnClickListener {
    private var _binding: FragmentFormUserPreferenceBinding? = null
    private val binding get() = _binding!!
    private lateinit var userModel: UserModel

    companion object {
        const val EXTRA_TYPE_FORM = "extra_type_form"
        const val EXTRA_RESULT = "extra_result"
        const val RESULT_CODE = 101

        const val TYPE_ADD = 1
        const val TYPE_EDIT = 2

        private const val FIELD_REQUIRED = "Field tidak boleh kosong"
        private const val FIELD_IS_NOT_VALID = "Email tidak valid"
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFormUserPreferenceBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val formType = arguments?.getInt(EXTRA_TYPE_FORM, 0) ?: 0
        @Suppress("DEPRECATION")
        userModel = arguments?.getParcelable("USER") ?: UserModel()

        var actionBarTitle = ""
        var btnTitle = ""

        when (formType) {
            TYPE_ADD -> {
                actionBarTitle = "Tambah Baru"
                btnTitle = "Simpan"
            }
            TYPE_EDIT -> {
                actionBarTitle = "Ubah"
                btnTitle = "Update"
                showPreferenceInForm()
            }
        }

        activity?.title = actionBarTitle
        binding.btnSave.text = btnTitle

        binding.btnSave.setOnClickListener(this)
    }

    private fun showPreferenceInForm() {
        binding.edtName.setText(userModel.fullName)
        binding.edtNim.setText(userModel.nim)
        binding.edtProdi.setText(userModel.prodi)
        binding.edtFaculty.setText(userModel.fakultas)
        binding.edtUniversity.setText(userModel.universitas)
        binding.edtEmail.setText(userModel.email)
    }

    override fun onClick(view: View) {
        if (view.id == R.id.btn_save) {
            val name = binding.edtName.text.toString().trim()
            val nim = binding.edtNim.text.toString().trim()
            val prodi = binding.edtProdi.text.toString().trim()
            val faculty = binding.edtFaculty.text.toString().trim()
            val university = binding.edtUniversity.text.toString().trim()
            val email = binding.edtEmail.text.toString().trim()

            if (name.isEmpty()) {
                binding.edtName.error = FIELD_REQUIRED
                return
            }

            if (email.isEmpty()) {
                binding.edtEmail.error = FIELD_REQUIRED
                return
            }

            if (!isValidEmail(email)) {
                binding.edtEmail.error = FIELD_IS_NOT_VALID
                return
            }

            if (nim.isEmpty()) {
                binding.edtNim.error = FIELD_REQUIRED
                return
            }

            if (prodi.isEmpty()) {
                binding.edtProdi.error = FIELD_REQUIRED
                return
            }

            if (faculty.isEmpty()) {
                binding.edtFaculty.error = FIELD_REQUIRED
                return
            }

            if (university.isEmpty()) {
                binding.edtUniversity.error = FIELD_REQUIRED
                return
            }

            saveUser(name, email, nim, prodi, faculty, university)

            val resultIntent = Intent()
            resultIntent.putExtra(EXTRA_RESULT, userModel)
            requireActivity().setResult(RESULT_CODE, resultIntent)

            findNavController().popBackStack()
        }
    }

    private fun saveUser(name: String, email: String, nim: String, prodi: String, faculty: String, university: String) {
        val userPreference = UserPreference(requireActivity())

        userModel.fullName = name
        userModel.nim = nim
        userModel.prodi = prodi
        userModel.fakultas = faculty
        userModel.universitas = university
        userModel.email = email

        userPreference.setUser(userModel)
        Toast.makeText(requireActivity(), "Data tersimpan", Toast.LENGTH_SHORT).show()
    }

    private fun isValidEmail(email: CharSequence): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    @Deprecated("Deprecated in Java")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            requireActivity().finish()
        }
        @Suppress("DEPRECATION")
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}