package com.dreamyprogrammer.mycosmos.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.dreamyprogrammer.mycosmos.databinding.PictureOfTheDayFragmentBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior

class PictureOfTheDayFragment : Fragment() {

    private var _binding: PictureOfTheDayFragmentBinding? = null
    private val binding: PictureOfTheDayFragmentBinding get() = _binding!!

    private val viewModel by lazy { ViewModelProvider(this).get(PictureOfTheDayViewModel::class.java) }

    companion object {
        fun newInstance() = PictureOfTheDayFragment()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = PictureOfTheDayFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.reloadPicture()
        binding.apply {
            viewModel.pictureOfTheDay.observe(viewLifecycleOwner) { picture ->
                textViewPictureOfTheDay.text = picture.explanation

                if (picture.isImage) {
                    Glide.with(root)
                        .load(picture.url)
                        .centerCrop()
                        .into(imageViewPictureOfTheDay)
                }
                imageViewPictureOfTheDay.isVisible = picture.isImage
            }

            chipGroupPictureOfTheDay.setOnCheckedChangeListener { _, _ ->
                viewModel.reloadPicture()
            }

            val bottomSheetBehavior: BottomSheetBehavior<FrameLayout> =
                BottomSheetBehavior.from(bottomSheetFrameLayout)
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
            bottomSheetFrameLayout.setOnClickListener {
                bottomSheetBehavior.state = when (bottomSheetBehavior.state) {
                    BottomSheetBehavior.STATE_COLLAPSED -> BottomSheetBehavior.STATE_EXPANDED
                    BottomSheetBehavior.STATE_EXPANDED -> BottomSheetBehavior.STATE_COLLAPSED
                    else -> BottomSheetBehavior.STATE_EXPANDED
                }

            }
        }
    }


}