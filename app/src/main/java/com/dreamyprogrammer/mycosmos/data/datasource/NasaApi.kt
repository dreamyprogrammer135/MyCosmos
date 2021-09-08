package com.dreamyprogrammer.mycosmos.data.datasource

import com.dreamyprogrammer.mycosmos.BuildConfig
import com.dreamyprogrammer.mycosmos.data.Curiosity
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NasaApi {

    @GET("planetary/apod")
    fun getPictureOfTheDay(@Query("api_key") apiKey: String): Call<PictureOfTheDayDate>

    @GET("/mars-photos/api/v1/rovers/curiosity/photos?sol=1000")
    fun getCuriousityPhotos(
        @Query("page") page: Int = 1,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
    ): Call<Curiosity>
}