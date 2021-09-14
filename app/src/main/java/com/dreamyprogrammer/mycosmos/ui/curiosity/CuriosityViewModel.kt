package com.dreamyprogrammer.mycosmos.ui.curiosity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dreamyprogrammer.mycosmos.RetrofitHolder
import com.dreamyprogrammer.mycosmos.data.Curiosity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CuriosityViewModel : ViewModel() {
    private var _curiosity = MutableLiveData<Curiosity>()
    val curiosity: LiveData<Curiosity> = _curiosity
    private val service by lazy { RetrofitHolder.getNasaApiService() }

    fun reloadPictures() {

        service.getCuriousityPhotos()
            .enqueue(object : Callback<Curiosity> {
                override fun onResponse(
                    call: Call<Curiosity>,
                    response: Response<Curiosity>
                ) {
                    if (response.isSuccessful) {
                        _curiosity.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<Curiosity>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            })
    }

}