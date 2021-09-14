package com.dreamyprogrammer.mycosmos.ui.wiki

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dreamyprogrammer.mycosmos.databinding.FragmentWikiBinding

private const val WIKI_URL = "https://en.wikipedia.org/wiki/"

class WikiFragment : Fragment() {
    var _binding: FragmentWikiBinding? = null
    val binding: FragmentWikiBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWikiBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.inputWiki.setEndIconOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW).apply {
                data =
                    Uri.parse(WIKI_URL + binding.inputEditTextWiki.text.toString())
            })
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}