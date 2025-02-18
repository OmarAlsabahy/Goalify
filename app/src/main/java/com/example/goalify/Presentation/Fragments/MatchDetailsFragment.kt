package com.example.goalify.Presentation.Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.goalify.Data.Enums.MatchDetailsSelectors
import com.example.goalify.Data.Models.MatchesResponseItem
import com.example.goalify.Presentation.Adapters.MatchDetailsSelectorsAdapter
import com.example.goalify.Presentation.ClickListeners.MatchDetailsSelectorsClickListener
import com.example.goalify.Presentation.ViewModels.MatchDetailsViewModel
import com.example.goalify.R
import com.example.goalify.databinding.FragmentMatchDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MatchDetailsFragment : Fragment() , MatchDetailsSelectorsClickListener {
    lateinit var binding:FragmentMatchDetailsBinding
    val viewModel : MatchDetailsViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_match_details, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentMatchDetailsBinding.bind(view)
        val match = MatchDetailsFragmentArgs.fromBundle(requireArguments()).match
        viewModel.setMatch(match)
        navigateToSelector(MatchDetailsSelectors.Stats.name)
        //observe match
        viewModel.match.observe(viewLifecycleOwner){match->
            setData(match)
        }

        //observe selectors
        viewModel.selectors.observe(viewLifecycleOwner){selectors->
            val adapter = MatchDetailsSelectorsAdapter(selectors,this)
            binding.selectorsRecycler.adapter = adapter
        }
    }


    private fun navigateToSelector(selector: String) {
        viewModel.match.observe(viewLifecycleOwner){match->
                   when(selector){
                       MatchDetailsSelectors.Stats.name->{
                           val fragment = StatsFragment()
                           val args = Bundle()
                           args.putParcelable("match",match)
                           fragment.arguments = args
                           childFragmentManager.beginTransaction()
                               .replace(R.id.fragmentsContainer , fragment)
                               .commit()

                       }MatchDetailsSelectors.LineUp.name->{
                       val fragment = LineUpFragment()
                       val args = Bundle()
                       args.putParcelable("match",match)
                       fragment.arguments = args
                       childFragmentManager.beginTransaction()
                           .replace(R.id.fragmentsContainer,fragment)
                           .commit()

                   }MatchDetailsSelectors.Summary.name->{
                       val fragment = SummaryFragment()
                       val args = Bundle()
                       args.putParcelable("match",match)
                       fragment.arguments = args
                       childFragmentManager.beginTransaction()
                           .replace(R.id.fragmentsContainer , fragment)
                           .commit()
                   }
                   }


        }

    }


    private fun setData(match: MatchesResponseItem?) {
        binding.competitionName.text = match?.league_name
        binding.homeTeamName.text = match?.match_hometeam_name
        binding.matchRound.text = "Round: ${match?.match_round}"
        binding.matchScore.text = "${match?.match_hometeam_score} : ${match?.match_awayteam_score}"
        binding.matchStatue.text = match?.match_status
        binding.awayTeamName.text = match?.match_awayteam_name
        binding.stadium.text = match?.match_stadium
        Glide.with(requireContext())
            .load(match?.team_home_badge)
            .into(binding.homeTeamImage)

        Glide.with(requireContext())
            .load(match?.team_away_badge)
            .into(binding.awayTeamImage)
    }

    override fun onSelectorClicked(selector: String) {
        navigateToSelector(selector)
    }
}