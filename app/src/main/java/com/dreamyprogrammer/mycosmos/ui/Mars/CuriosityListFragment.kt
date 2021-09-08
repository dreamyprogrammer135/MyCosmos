package com.dreamyprogrammer.mycosmos.ui.Mars

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import com.dreamyprogrammer.mycosmos.adapter.CuriosityPhotoListViewPagerAdapter
import com.dreamyprogrammer.mycosmos.databinding.CuriosityListFragmentBinding

val numOfCuriosityPhotos = MutableLiveData<Int>()

class CuriosityListFragment : Fragment() {
    private var _binding: CuriosityListFragmentBinding? = null
    private val binding: CuriosityListFragmentBinding get() = _binding!!



    private val viewModel by lazy {
        ViewModelProvider(this).get(CuriosityViewModel::class.java)
    }

    companion object {
        fun newInstance() = CuriosityListFragment()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = CuriosityListFragmentBinding.inflate(inflater)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.curiosity.observe(viewLifecycleOwner) { photos ->
            binding.viewPagerCuriosity.adapter =
                CuriosityPhotoListViewPagerAdapter(requireActivity(), photos)
             numOfCuriosityPhotos.value = photos.size
        }
    }

}