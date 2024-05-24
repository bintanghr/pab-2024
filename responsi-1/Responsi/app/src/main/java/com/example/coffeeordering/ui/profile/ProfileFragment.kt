package com.example.coffeeordering.ui.profile

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.coffeeordering.R

class ProfileFragment : Fragment(), View.OnClickListener {
    private val fullName = "Bintang Harida Ramadhan"
    private val nim = "L0122034"
    private val prodi = "Informatika, 2022"
    private val fakultas = "Teknologi Informasi dan Sains Data"
    private val universitas = "Universitas Sebelas Maret"
    private val email = "haridaramadhan@gmail.com"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_profile, container, false)

        val tvFullName : TextView = rootView.findViewById(R.id.full_name)
        val tvNim : TextView = rootView.findViewById(R.id.nim)
        val tvProdi : TextView = rootView.findViewById(R.id.prodi)
        val tvUniversitas : TextView = rootView.findViewById(R.id.universitas)
        val tvFakultas : TextView = rootView.findViewById(R.id.fakultas)
        val tvEmail : TextView = rootView.findViewById(R.id.email)
        val btnShare : Button = rootView.findViewById(R.id.btn_share)
        btnShare.setOnClickListener(this)

        tvFullName.text = fullName
        tvNim.text = nim
        tvProdi.text = prodi
        tvFakultas.text = fakultas
        tvUniversitas.text = universitas
        tvEmail.text = email

        return rootView
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_share -> {
                val dataIntent = "$nim \n $fullName \n $prodi \n $fakultas \n $universitas"

                val shareIntent = Intent(Intent.ACTION_SEND).apply {
                    type = "text/plain"
                    putExtra(Intent.EXTRA_EMAIL, arrayOf("haridaramadhan@gmail.com"))
                    putExtra(Intent.EXTRA_SUBJECT, "pengen kenalan")
                    putExtra(Intent.EXTRA_TEXT, dataIntent)
                }
                startActivity(Intent.createChooser(shareIntent, "Share via..."))
            }
        }
    }
}