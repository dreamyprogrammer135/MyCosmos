package com.dreamyprogrammer.mycosmos.ui.Mars

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.dreamyprogrammer.mycosmos.R
import com.dreamyprogrammer.mycosmos.data.Photo
import com.dreamyprogrammer.mycosmos.databinding.CuriosityFragmentBinding

private const val PARAM_CURIOSITY = "curiosity"

class CuriousityFragment : Fragment() {
    private var curiosityPhoto: Photo? = null
    private var _binding: CuriosityFragmentBinding? = null
    private val binding: CuriosityFragmentBinding get() = _binding!!

    companion object {
        fun newInstance(curiosityPhoto: Photo) =
            CuriousityFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(PARAM_CURIOSITY,curiosityPhoto)
                }
            }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            curiosityPhoto = it.getParcelable<Photo>(PARAM_CURIOSITY)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = CuriosityFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        curiosityPhoto?.let {
            binding.apply {
                textViewCuriosity.text = it.earthDate

                Glide.with(root)
                    .load(it.imgSRC)
                    .centerCrop()
                    .into(imageViewCuriosity)
            }
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}