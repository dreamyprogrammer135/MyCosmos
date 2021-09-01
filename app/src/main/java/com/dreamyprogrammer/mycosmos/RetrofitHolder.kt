package com.dreamyprogrammer.mycosmos

import com.dreamyprogrammer.mycosmos.data.datasource.NasaApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitHolder {
    private const val BASE_URL = "https://api.nasa.gov/"
    private val service: NasaApi by lazy { retrofit.create(NasaApi::class.java) }

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
    }

    fun getNasaApiService() = service
}