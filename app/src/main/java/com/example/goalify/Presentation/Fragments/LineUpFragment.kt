package com.example.goalify.Presentation.Fragments

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.goalify.Data.Models.AwayX
import com.example.goalify.Data.Models.HomeX
import com.example.goalify.Data.Models.MatchesResponseItem
import com.example.goalify.Data.Models.StartingLineup
import com.example.goalify.Data.Models.Substitute
import com.example.goalify.Presentation.Adapters.StartingLineUpAdapter
import com.example.goalify.Presentation.Adapters.SubstitutesAdapter
import com.example.goalify.Presentation.Adapters.SubstitutionsAdapter
import com.example.goalify.Presentation.ViewModels.LineUpViewModel
import com.example.goalify.R
import com.example.goalify.databinding.FragmentLineUpBinding


class LineUpFragment : Fragment() {
    lateinit var binding : FragmentLineUpBinding
    val viewModel : LineUpViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_line_up, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentLineUpBinding.bind(view)
        val args = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getParcelable("match",MatchesResponseItem::class.java)
        } else {
            arguments?.getParcelable("match")
        }
        viewModel.setMatchData(args!!)


        //observe match
        viewModel.match.observe(viewLifecycleOwner){match->
            binding.homeCoach.text = match.lineup.home.coach[0].lineup_player
            binding.awayCoach.text = match.lineup.away.coach[0].lineup_player
            setStartingLineUpRecycler(match.lineup.home.starting_lineups,match.lineup.away.starting_lineups)
            setSubstitutesRecycler(match.lineup.home.substitutes , match.lineup.away.substitutes)
            setSubstitutionsRecycler(match.substitutions.home , match.substitutions.away)
        }
    }

    private fun setSubstitutionsRecycler(home: List<HomeX>, away: List<AwayX>) {
        val adapter = SubstitutionsAdapter(home , away)
        binding.substitutionsRecycler.adapter = adapter
    }

    private fun setSubstitutesRecycler(home: List<Substitute>, away: List<Substitute>) {
          if (home.isNotEmpty()&&away.isNotEmpty()){
              val adapter = SubstitutesAdapter(home,away)
              binding.substitutesRecycler.adapter = adapter
              binding.substitutesRecycler.visibility = View.VISIBLE
              binding.txtSubstitutesnoLineUpFounded.visibility = View.GONE
          }else{
              binding.substitutesRecycler.visibility = View.GONE
              binding.txtSubstitutesnoLineUpFounded.visibility = View.VISIBLE
          }
    }

    private fun setStartingLineUpRecycler(
        home: List<StartingLineup>,
        away: List<StartingLineup>
    ){
        if (home.isNotEmpty() && away.isNotEmpty()){
            val adapter = StartingLineUpAdapter(home , away)
            binding.startingLineUpRecycler.adapter = adapter
            binding.startingLineUpRecycler.visibility = View.VISIBLE
            binding.txtNoLineUpFounded.visibility = View.GONE
        }else{
            binding.startingLineUpRecycler.visibility = View.GONE
            binding.txtNoLineUpFounded.visibility = View.VISIBLE

        }
    }
}