package com.example.goalify.Presentation.Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.goalify.Presentation.Adapters.TopScorerAdapter
import com.example.goalify.Presentation.ViewModels.TopScorerViewModel
import com.example.goalify.R
import com.example.goalify.databinding.FragmentTopScorerBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TopScorerFragment : Fragment() {
    lateinit var binding:FragmentTopScorerBinding
    val viewModel : TopScorerViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_top_scorer, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentTopScorerBinding.bind(view)
        val competitionId = TopScorerFragmentArgs.fromBundle(requireArguments()).competitionId
        viewModel.getTopScorer(competitionId)

        //observe players
        viewModel.topScorerWithImage.observe(viewLifecycleOwner){players->
            val adapter = TopScorerAdapter(players)
            binding.topScorerRecycler.adapter = adapter
            binding.progress.visibility = View.GONE
        }
    }
}