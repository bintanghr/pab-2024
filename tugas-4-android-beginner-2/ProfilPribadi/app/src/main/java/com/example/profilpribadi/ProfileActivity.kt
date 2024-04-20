package com.example.profilpribadi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ProfileActivity : AppCompatActivity(), View.OnClickListener {
    companion object{
        const val EXTRA_NIM = "extra_nim"
        const val EXTRA_NAME = "extra_name"
        const val EXTRA_BATCH = "extra_batch"
        const val EXTRA_MAJOR = "extra_major"
        const val EXTRA_SELF_DESCRIPTION = "extra_self_description"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        enableEdgeToEdge()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val tvProfileData : TextView = findViewById(R.id.tv_profile_data)
        val tvSelfDescription : TextView = findViewById(R.id.tv_self_description)
        val btnShare : Button = findViewById(R.id.btn_share)
        btnShare.setOnClickListener(this)

        val nim = intent.getStringExtra(EXTRA_NIM)
        val name = intent.getStringExtra(EXTRA_NAME)
        val batch = intent.getIntExtra(EXTRA_BATCH, 0)
        val major = intent.getStringExtra(EXTRA_MAJOR)
        val selfDescription = intent.getStringExtra(EXTRA_SELF_DESCRIPTION)

        val info = """
            $nim
            
            $name
            
            $major, $batch
            
            About Me
        """.trimIndent()

        tvProfileData.text = info
        tvSelfDescription.text = selfDescription
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.btn_share -> {
                val nim = intent.getStringExtra(EXTRA_NIM)
                val name = intent.getStringExtra(EXTRA_NAME)
                val batch = intent.getIntExtra(EXTRA_BATCH, 0)
                val major = intent.getStringExtra(EXTRA_MAJOR)
                val selfDescription = intent.getStringExtra(EXTRA_SELF_DESCRIPTION)

                val dataIntent = "$nim \n $name \n $batch \n $major \n $selfDescription"

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