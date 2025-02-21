package com.example.goalify.Presentation.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.goalify.Presentation.Adapters.TopAssistAdapter
import com.example.goalify.Presentation.Adapters.TopScorerAdapter
import com.example.goalify.Presentation.ViewModels.TopAssistViewModel
import com.example.goalify.R
import com.example.goalify.databinding.FragmentTopAssistBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TopAssistFragment : Fragment() {
    lateinit var binding: FragmentTopAssistBinding
    val viewModel : TopAssistViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_top_assist, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentTopAssistBinding.bind(view)
        val competitionId = arguments?.getInt("competitionId")
        viewModel.getTopAssist(competitionId!!)

        viewModel.topAssistWithImage.observe(viewLifecycleOwner){players->
            val adapter = TopAssistAdapter(players)
            binding.topAssistRecycler.adapter = adapter
            binding.progress.visibility = View.GONE
        }

    }
}