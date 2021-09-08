package com.dreamyprogrammer.mycosmos.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Curiosity(
    val photos: List<Photo>
) : Parcelable
