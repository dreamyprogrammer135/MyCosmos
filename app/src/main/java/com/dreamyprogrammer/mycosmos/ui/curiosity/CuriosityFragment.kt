package com.dreamyprogrammer.mycosmos.ui.curiosity

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.dreamyprogrammer.mycosmos.R
import com.dreamyprogrammer.mycosmos.adapter.CuriosityAdapter
import com.dreamyprogrammer.mycosmos.data.Photo
import com.dreamyprogrammer.mycosmos.databinding.CuriosityFragmentBinding

class CuriousityFragment : Fragment() {
    private var _binding: CuriosityFragmentBinding? = null
    private val binding: CuriosityFragmentBinding get() = _binding!!
    private val adapter by lazy { CuriosityAdapter() }

    companion object {
        fun newInstance() = CuriousityFragment()
    }

    private val viewModel by lazy { ViewModelProvider(this).get(CuriosityViewModel::class.java) }

    private val contract by lazy { requireActivity() as Contract }

    interface Contract {
        fun onItemClickListener(curiosity: Photo, view: View)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        TransitionInflater.from(requireContext()).let {
            exitTransition = it.inflateTransition(R.transition.zoom_exit)
            enterTransition = it.inflateTransition(R.transition.zoom_enter)
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
        viewModel.reloadPictures()
        val recyclerMars: RecyclerView = binding.curiosityList

        recyclerMars.adapter = adapter
        binding.apply {
            viewModel.curiosity.observe(viewLifecycleOwner) { pictures ->
                adapter.clear()
                adapter.addItems(pictures)
            }
            adapter.listener = CuriosityAdapter.OnItemClickListener { curiosity ->
                contract.onItemClickListener(curiosity, view)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}