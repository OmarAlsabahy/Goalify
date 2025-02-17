package com.example.goalify.Presentation.Fragments

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.goalify.Data.Models.MatchesResponseItem
import com.example.goalify.Presentation.Adapters.GoalScorerAdapter
import com.example.goalify.Presentation.ViewModels.SummaryViewModel
import com.example.goalify.R
import com.example.goalify.databinding.FragmentSummaryBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SummaryFragment : Fragment() {
    lateinit var binding : FragmentSummaryBinding
    val viewModel : SummaryViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_summary, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentSummaryBinding.bind(view)
        val args = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getParcelable("match", MatchesResponseItem::class.java)
        }else{
            arguments?.getParcelable("match")
        }
        viewModel.setMatch(args!!)

        //observe home goal scorer
        viewModel.homeScorer.observe(viewLifecycleOwner){homeScorer->


            //observe away goal scorer
            viewModel.awayScorer.observe(viewLifecycleOwner){awayScorer->

                val adapter = GoalScorerAdapter(homeScorer , awayScorer)
                binding.GoalScorerRecycler.adapter = adapter
            }


        }


    }
}