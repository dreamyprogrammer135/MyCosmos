package com.dreamyprogrammer.mycosmos

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.dreamyprogrammer.mycosmos.databinding.MainActivityBinding
import com.dreamyprogrammer.mycosmos.ui.main.PictureOfTheDayFragment
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {
    private lateinit var binding: MainActivityBinding
    //TODO С этим надо что то сделать (шарепреференс или enum) надо подумать

    private var basicTheme = R.style.Theme_MyCosmos
    private var marsTheme = R.style.Theme_MyCosmos_Mars
    private var currentTheme: Boolean = false

    private val bottomView: BottomNavigationView by lazy { binding.bottomNavigationView }
    private val pictureOfTheDayFragment by lazy { PictureOfTheDayFragment() }
    private val fragmentSettings by lazy { SettingsFragment() }

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        currentTheme = receivePreference()
        setMyThem(currentTheme)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, PictureOfTheDayFragment.newInstance())
                .commitNow()
        }
        prepareBottomNavigationView()
    }

    fun setMyThem(them: Boolean) {
        if (them) {
            setTheme(marsTheme)
        } else {
            setTheme(basicTheme)
        }
        if (them != currentTheme) {
            recreate()
        }

    }


    private fun receivePreference(): Boolean {
        val preferenceManager =
            androidx.preference.PreferenceManager.getDefaultSharedPreferences(application)
        return preferenceManager.getBoolean("select_theme", true)
    }

    private fun prepareBottomNavigationView() {
        bottomView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menu_bottom_item_picture -> {
                    showPicture()
                    if (currentTheme != receivePreference()) {
                        setMyThem(receivePreference())
                    }
                }
                R.id.app_bar_settings -> {
                    showSettings()
                }
                else -> {
                    return@setOnItemSelectedListener false
                }
            }
            true
        }
    }


    private fun showPicture() {
        supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)

        supportFragmentManager.beginTransaction()
            .replace(R.id.container, pictureOfTheDayFragment)
            .commit()
    }

    private fun showSettings() {
        supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)

        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragmentSettings)
            .commit()
    }

}