package com.dreamyprogrammer.mycosmos.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.dreamyprogrammer.mycosmos.data.Photo
import com.dreamyprogrammer.mycosmos.ui.Mars.CuriousityFragment

class CuriosityPhotoListViewPagerAdapter(
    fragmentActivity: FragmentActivity,
    private val curiosityPhotos: List<Photo>,
) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int = curiosityPhotos.size

    override fun createFragment(position: Int): Fragment =
        CuriousityFragment.newInstance(curiosityPhotos[position])
}