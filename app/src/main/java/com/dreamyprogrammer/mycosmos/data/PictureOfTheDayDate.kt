package com.dreamyprogrammer.mycosmos.data.datasource

import com.google.gson.annotations.SerializedName

data class PictureOfTheDayDate(
    @field:SerializedName("copyright") val copyright: String?,
    @field:SerializedName("date") val date: String?,
    @field:SerializedName("explanation") val explanation: String?,
    @field:SerializedName("media_type") val mediaType: String?,
    @field:SerializedName("title") val title: String?,
    @field:SerializedName("url") val url: String?,
    @field:SerializedName("hdurl") val hdUrl: String?
) {
    val isImage: Boolean get() = mediaType?.equals("image") == true
}
