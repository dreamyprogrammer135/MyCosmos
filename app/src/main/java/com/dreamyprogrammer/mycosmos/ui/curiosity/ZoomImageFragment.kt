package com.dreamyprogrammer.mycosmos.ui.curiosity

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.dreamyprogrammer.mycosmos.R
import com.dreamyprogrammer.mycosmos.data.Photo
import com.dreamyprogrammer.mycosmos.databinding.ZoomImageFragmentBinding

class ZoomImageFragment : Fragment() {
    private var _binding: ZoomImageFragmentBinding? = null
    private val binding: ZoomImageFragmentBinding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        TransitionInflater.from(requireContext()).let {
            exitTransition = it.inflateTransition(R.transition.zoom_enter)
            enterTransition = it.inflateTransition(R.transition.zoom_exit)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {


        _binding = ZoomImageFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val curiosity = arguments?.getParcelable<Photo>(ARGUMENT_KEY_CURIOSITY)
        curiosity?.let {
            Glide.with(binding.root)
                .load(curiosity.imgSrc)
                .fitCenter()
                .into(binding.zoomImageView)
        }
    }


    companion object {
        private const val ARGUMENT_KEY_CURIOSITY = "curiosity"
        fun newInstance(curiosity: Photo) = ZoomImageFragment().apply {
            arguments = Bundle().apply {
                putParcelable(ARGUMENT_KEY_CURIOSITY, curiosity)
            }
        }
    }
}