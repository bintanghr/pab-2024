package com.example.profilpribadi

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        enableEdgeToEdge()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val imageView : ImageView = findViewById(R.id.foto_bintang)
        val btnGoToProfile : Button = findViewById(R.id.btn_profile)
        val btnGitHub : Button = findViewById(R.id.btn_git_hub)
        btnGoToProfile.setOnClickListener(this)
        btnGitHub.setOnClickListener(this)

        Glide.with(this)
            .load(R.drawable.photo_foreground)
            .apply(RequestOptions().transform(RoundedCorners(24)))
            .into(imageView)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
                R.id.btn_profile -> {
                val profileIntent = Intent(this@MainActivity, ProfileActivity::class.java)
                profileIntent.putExtra(ProfileActivity.EXTRA_NIM, "L0122034")
                profileIntent.putExtra(ProfileActivity.EXTRA_NAME, "Bintang Harida Ramadhan")
                profileIntent.putExtra(ProfileActivity.EXTRA_MAJOR, "Informatika")
                profileIntent.putExtra(ProfileActivity.EXTRA_BATCH, 2022)
                profileIntent.putExtra(ProfileActivity.EXTRA_SELF_DESCRIPTION, "Saya suka nongkrong")
                startActivity(profileIntent)
            }
            R.id.btn_git_hub -> {
                val gitHubIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/bintanghr"))
                startActivity(gitHubIntent)
            }
        }
    }
}