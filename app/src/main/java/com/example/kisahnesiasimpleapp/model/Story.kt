package com.example.kisahnesiasimpleapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Story(
    val title: String,
    val synopsis: String,
    val image: Int,
    val region : String,
    val genre : String,
) : Parcelable