package com.example.coffeeordering.ui.profile

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserModel (
    var fullName: String? = null,
    var nim: String? = null,
    var prodi: String? = null,
    var fakultas: String? = null,
    var universitas: String? = null,
    var email: String? = null
) : Parcelable
