package com.dreamyprogrammer.mycosmos.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Photo(
    @field:SerializedName("img_src") val imgSRC: String?,
    @field:SerializedName("earth_date") val earthDate: String?,
) : Parcelable