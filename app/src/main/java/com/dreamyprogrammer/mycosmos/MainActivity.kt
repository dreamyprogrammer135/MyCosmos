package com.dreamyprogrammer.mycosmos

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.preference.PreferenceManager
import com.dreamyprogrammer.mycosmos.databinding.MainActivityBinding
import com.dreamyprogrammer.mycosmos.ui.PictureOfTheDay.PictureOfTheDayFragment
import com.dreamyprogrammer.mycosmos.ui.curiosity.CuriousityFragment
import com.dreamyprogrammer.mycosmos.ui.wiki.WikiFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlin.reflect.KClass


private const val SP_KEY_SELECT_THEME = "select_theme"

class MainActivity : AppCompatActivity() {
    private lateinit var binding: MainActivityBinding
    //TODO С этим надо что то сделать (шарепреференс или enum) надо подумать

    private var basicTheme = R.style.Theme_MyCosmos
    private var marsTheme = R.style.Theme_MyCosmos_Mars
    private var currentTheme: Boolean = false

    private val bottomView: BottomNavigationView by lazy { binding.bottomNavigationView }
    private val pictureOfTheDayFragment by lazy { PictureOfTheDayFragment() }
    private val curiosityFragment by lazy { CuriousityFragment() }
    private val settingsFragment by lazy { SettingsFragment() }
    private val fragmentWiki by lazy { WikiFragment() }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        currentTheme = receiveSharedPreferences()
        setMyTheme(currentTheme)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, PictureOfTheDayFragment.newInstance())
                .commitNow()
        }
        prepareBottomNavigationView()

    }

    fun setMyTheme(theme: Boolean) {
        if (theme) {
            setTheme(marsTheme)
        } else {
            setTheme(basicTheme)
        }
        if (theme != currentTheme) {
            recreate()
        }

    }

    private fun receiveSharedPreferences(): Boolean {
        val preferenceManager =
            PreferenceManager.getDefaultSharedPreferences(application)
        return preferenceManager.getBoolean(SP_KEY_SELECT_THEME, false)
    }

    private fun prepareBottomNavigationView() {
        bottomView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menu_bottom_item_picture -> {
                    showPicture()
                    if (currentTheme != receiveSharedPreferences()) {
                        setMyTheme(receiveSharedPreferences())
                    }
                }
                R.id.menu_bottom_item_mars -> {
                    showCuriosity()
                }
                R.id.menu_bottom_item_search -> {
                    showWiki()
                }
                R.id.menu_bottom_item_settings -> {
                    showSettings()
                }
                else -> {
                    return@setOnItemSelectedListener false
                }
            }
            true
        }
    }

    private fun showWiki() {
        supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)

        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragmentWiki)
            .commit()

    }


    private fun showPicture() {
        supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)

        supportFragmentManager.beginTransaction()
            .replace(R.id.container, pictureOfTheDayFragment)
            .commit()
    }

    private fun showCuriosity() {
        supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)

        supportFragmentManager.beginTransaction()
            .replace(R.id.container, curiosityFragment)
            .commit()
    }

    private fun showSettings() {
        supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)

        supportFragmentManager.beginTransaction()
            .replace(R.id.container, settingsFragment)
            .commit()
    }

}