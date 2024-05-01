package com.example.coffeeorderingapp

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Menu(
    val name: String,
    val price: String,
    val desc: String,
    val img: Int
) : Parcelable
