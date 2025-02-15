package com.example.goalify.Presentation.Fragments

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.goalify.Data.Enums.StatisticsType
import com.example.goalify.Data.Models.MatchesResponseItem
import com.example.goalify.Presentation.ViewModels.StateViewModel
import com.example.goalify.R
import com.example.goalify.databinding.FragmentStatsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StatsFragment : Fragment() {
    lateinit var binding:FragmentStatsBinding
    val viewModel : StateViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_stats, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentStatsBinding.bind(view)
        val args = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getParcelable("match",MatchesResponseItem::class.java)
        } else {
            arguments?.getParcelable("match")
        }
        setData(args)


        //observe homeShotsOnTarget
        viewModel.homeShotsOnTarget.observe(viewLifecycleOwner){shots->
            binding.homeTeamShotsOnGoal.text = shots.toString()
        }


        //observe awayHomeShotsOnTarget
        viewModel.awayShotsOnTarget.observe(viewLifecycleOwner){shots->
            binding.awayTeamShotsOnGoal.text = shots.toString()
        }

        //observe home total shots
        viewModel.homeTotalShots.observe(viewLifecycleOwner){shots->
            binding.homeTotalShots.text = shots.toString()
        }


        //observe away total shots
        viewModel.awayTotalShots.observe(viewLifecycleOwner){shots->
            binding.awayTotalShots.text = shots.toString()
        }

        //observe home ball possession
        viewModel.homePossession.observe(viewLifecycleOwner){possession->
            binding.homePossession.text = possession
        }

        //observer away ball possession
        viewModel.awayPossession.observe(viewLifecycleOwner){possession->
            binding.awayPossession.text = possession
        }

        //observe home yellow cards
        viewModel.homeYellowCards.observe(viewLifecycleOwner){cards->
            binding.homeYellowCards.text = cards
        }

        //observer away yellow cards
        viewModel.awayYellowCards.observe(viewLifecycleOwner){cards->
            binding.awayYellowCards.text = cards
        }

        //observe home red cards
        viewModel.homeRedCards.observe(viewLifecycleOwner) { cards ->
            binding.homeRedCards.text = cards
        }

        //observe away red cards
        viewModel.awayRedCards.observe(viewLifecycleOwner) { cards ->
            binding.awayRedCards.text = cards
        }

        //observe home corners
        viewModel.homeCorners.observe(viewLifecycleOwner){corners->
            binding.homeCorners.text = corners.toString()
        }

        //observe away corners
        viewModel.awayCorners.observe(viewLifecycleOwner){corners->
            binding.awayCorners.text = corners.toString()
        }
        //observe home fouls
        viewModel.homeFouls.observe(viewLifecycleOwner){fouls->
            binding.homeTeamFouls.text = fouls
        }

        //observe away fouls
        viewModel.awayFouls.observe(viewLifecycleOwner){fouls->
            binding.awayTeamFouls.text = fouls
        }

        //observe home penalties
        viewModel.homePenalties.observe(viewLifecycleOwner){penalties->
            binding.homePenalties.text = penalties.toString()
        }

        //observe away penalties
        viewModel.awayPenalties.observe(viewLifecycleOwner){penalties->
            binding.awayPenalties.text = penalties.toString()
        }

        //observe home offsides
        viewModel.homeOffsides.observe(viewLifecycleOwner){offsides->
            binding.homeOffsides.text = offsides
        }

        //observe away offsides
        viewModel.awayOffsides.observe(viewLifecycleOwner){offsides->
            binding.awayOffsides.text = offsides
        }

    }

    private fun setData(match: MatchesResponseItem?) {
        var homeShotsOnTarget = mutableListOf<String>()
        var awayShotsOnTarget = mutableListOf<String>()
        var homeCorners = mutableListOf<String>()
        var awayCorner = mutableListOf<String>()
        var homePenalties = mutableListOf<String>()
        var awayPenalties = mutableListOf<String>()
        for (state in match!!.statistics){
            when(state.type){
                StatisticsType.OnTarget.type->{
                    homeShotsOnTarget.add(state.home)
                    awayShotsOnTarget.add(state.away)
                }StatisticsType.TotalSHots.type->{
                    viewModel.setTotalShots(state.home.toInt(),state.away.toInt())
                }StatisticsType.BallPossession.type->{
                    viewModel.setBallPossession(state.home , state.away)
                }StatisticsType.YellowCards.type->{
                    viewModel.setYellowCards(state.home,state.away)
                }StatisticsType.RedCards.type->{
                    viewModel.setRedCards(state.home,state.away)
                }StatisticsType.Corners.type->{
                    homeCorners.add(state.home)
                    awayCorner.add(state.away)
                }StatisticsType.Fouls.type->{
                    viewModel.setFouls(state.home,state.away)
                }StatisticsType.Penalty.type->{
                    homePenalties.add(state.home)
                    awayPenalties.add(state.away)
                }StatisticsType.Fouls.type->{
                    viewModel.setFouls(state.home,state.away)
                }StatisticsType.Offsides.type->{
                    viewModel.setOffsides(state.home , state.away)
                }
            }
        }

        viewModel.getHomeShotsOnTarget(homeShotsOnTarget)
        viewModel.getAwayShotsOnTarget(awayShotsOnTarget)
        viewModel.getHomeCorners(homeCorners)
        viewModel.getAwayCorners(awayCorner)
        viewModel.getHomePenalties(homePenalties)
        viewModel.getAwayPenalties(awayPenalties)

    }
}