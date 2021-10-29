package com.whereisdarran.well.model

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Pokemon(
    val id: Int,
    val name: String,
    val description: String,
    val imageUrl: String
) : Parcelable
