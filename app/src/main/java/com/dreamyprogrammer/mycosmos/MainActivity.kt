package com.dreamyprogrammer.mycosmos

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dreamyprogrammer.mycosmos.databinding.MainActivityBinding
import com.dreamyprogrammer.mycosmos.ui.main.PictureOfTheDayFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: MainActivityBinding

    private val pictureOfTheDay by lazy { PictureOfTheDayFragment }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, PictureOfTheDayFragment.newInstance())
                .commitNow()
        }
    }
}