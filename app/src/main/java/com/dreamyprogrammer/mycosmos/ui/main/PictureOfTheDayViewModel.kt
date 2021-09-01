package com.dreamyprogrammer.mycosmos.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dreamyprogrammer.mycosmos.BuildConfig
import com.dreamyprogrammer.mycosmos.RetrofitHolder
import com.dreamyprogrammer.mycosmos.data.datasource.PictureOfTheDayDate
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PictureOfTheDayViewModel : ViewModel() {
    private var _pictureOfTheDay = MutableLiveData<PictureOfTheDayDate>()
    val pictureOfTheDay: LiveData<PictureOfTheDayDate> = _pictureOfTheDay
    private val service by lazy { RetrofitHolder.getNasaApiService() }

    fun getPicture() {
        val apiKey: String = BuildConfig.ApiKey

        service.getPictureOfTheDay(apiKey)
            .enqueue(object : Callback<PictureOfTheDayDate> {
                override fun onResponse(
                    call: Call<PictureOfTheDayDate>,
                    response: Response<PictureOfTheDayDate>,
                ) {
                    if (response.isSuccessful) {
                        _pictureOfTheDay.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<PictureOfTheDayDate>, t: Throwable) {
                    //TODO Надо подумать
                }
            })
    }
}