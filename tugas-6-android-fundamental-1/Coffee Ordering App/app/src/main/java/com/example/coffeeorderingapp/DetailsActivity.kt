package com.example.coffeeorderingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DetailsActivity : AppCompatActivity(), View.OnClickListener {
    companion object {
        const val EXTRA_NAME = "extra_name"
        const val EXTRA_PRICE = "extra_price"
        const val EXTRA_DESC = "extra_desc"
        const val EXTRA_IMG = "extra_img"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        enableEdgeToEdge()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val imgMenu : ImageView = findViewById(R.id.img_menu)
        val tvMenuPrice : TextView = findViewById(R.id.tv_menu_price)
        val tvMenuName : TextView = findViewById(R.id.tv_menu_name)
        val tvMenuDesc : TextView = findViewById(R.id.tv_menu_desc)
        val btnBack : ImageButton = findViewById(R.id.btn_back)
        btnBack.setOnClickListener(this)

        val img = intent.getIntExtra(EXTRA_IMG, 0)
        val price = intent.getStringExtra(EXTRA_PRICE)
        val name = intent.getStringExtra(EXTRA_NAME)
        val desc = intent.getStringExtra(EXTRA_DESC)
        val priceText = "Rp $price"

        imgMenu.setImageResource(img)
        tvMenuPrice.text = priceText
        tvMenuName.text = name
        tvMenuDesc.text = desc

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_back -> {
              finish()
            }
        }
    }
}