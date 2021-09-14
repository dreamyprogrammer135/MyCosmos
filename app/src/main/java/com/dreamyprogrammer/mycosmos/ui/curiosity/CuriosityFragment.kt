package com.dreamyprogrammer.mycosmos.ui.curiosity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.dreamyprogrammer.mycosmos.adapter.CuriosityAdapter
import com.dreamyprogrammer.mycosmos.databinding.CuriosityFragmentBinding

class CuriousityFragment : Fragment() {
    private var _binding: CuriosityFragmentBinding? = null
    private val binding: CuriosityFragmentBinding get() = _binding!!
    private val adapter by lazy { CuriosityAdapter() }

    companion object {
        fun newInstance() = CuriousityFragment()
    }

    private val viewModel by lazy { ViewModelProvider(this).get(CuriosityViewModel::class.java) }

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
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}