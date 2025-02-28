package com.example.goalify.Presentation.Fragments

import android.graphics.LinearGradient
import android.graphics.Shader
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.view.MenuProvider
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.goalify.Data.Enums.LeaguesId
import com.example.goalify.Data.Models.MatchesResponseItem
import com.example.goalify.Presentation.Adapters.LiveMatchesAdapter
import com.example.goalify.Presentation.Adapters.MatchesAdapter
import com.example.goalify.Presentation.Adapters.TopCompetitionAdapter
import com.example.goalify.Presentation.ClickListeners.MatchClickListener
import com.example.goalify.Presentation.ClickListeners.TopCompetitionClickListener
import com.example.goalify.Presentation.ViewModels.HomeViewModel
import com.example.goalify.R
import com.example.goalify.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@AndroidEntryPoint
class HomeFragment : Fragment() , TopCompetitionClickListener ,MatchClickListener {
    lateinit var binding: FragmentHomeBinding
    val viewModel : HomeViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }


    override fun onStart() {
        super.onStart()
        viewModel.setCompetitionId(LeaguesId.premierLeague.league_id)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentHomeBinding.bind(view)
        val paint = binding.txtLiveScore.paint
        val shader = LinearGradient(0f , 0f , paint.measureText(binding.txtLiveScore.text.toString()) ,
            binding.txtLiveScore.textSize , intArrayOf(resources.getColor(R.color.mainColor , null) ,
                resources.getColor(R.color.secondaryColor , null)) , null , Shader.TileMode.CLAMP )
        binding.txtLiveScore.paint.shader = shader

        val toolbar = view.findViewById<Toolbar>(R.id.main_toolBar)
        (activity as? AppCompatActivity)?.setSupportActionBar(toolbar)
        toolbar.overflowIcon = ContextCompat.getDrawable(requireContext() , R.drawable.overflowicon)


        //implement menu
        requireActivity().addMenuProvider(object:MenuProvider{
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.main_menu,menu)
                for(i in 0 until menu.size()){
                    val meuItem = menu.getItem(i).actionView
                    meuItem?.setBackgroundColor(ContextCompat.getColor(requireContext(),R.color.backgroundColor))
                }
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                when(menuItem.itemId){
                    R.id.top_scorer->{
                        viewModel.competitionId.observe(viewLifecycleOwner){competitionId->
                            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToTopScorerFragment(competitionId))
                        }
                    }
                }
                return true
            }
        },viewLifecycleOwner)
        //observeCompetitions
        viewModel.competitions.observe(viewLifecycleOwner){competitions->
            val adapter = TopCompetitionAdapter(competitions , this)
            binding.competitionsRecycler.adapter = adapter
        }

        //observe matches
        viewModel.matches.observe(viewLifecycleOwner){matches->
            if (!matches.isNullOrEmpty()){
                val adapter = MatchesAdapter(matches,this)
                binding.todayMatchesRecycler.adapter = adapter
                binding.txtNoEventFound.visibility = View.GONE
                binding.todayMatchesRecycler.visibility = View.VISIBLE
                binding.progress.visibility = View.GONE
            }
        }

        // observe match error
        viewModel.matchesError.observe(viewLifecycleOwner) { error ->
            binding.txtNoEventFound.visibility = View.VISIBLE
            binding.todayMatchesRecycler.visibility = View.GONE
            binding.progress.visibility = View.GONE
        }

        //observe live matches error
        viewModel.liveMatchesError.observe(viewLifecycleOwner){error->
            binding.txtNoLiveMatches.visibility = View.VISIBLE
            binding.liveMatchesRecycler.visibility = View.GONE
            binding.liveMatchesProgress.visibility = View.GONE
        }

        //observe liveMatches
        viewModel.liveMatches.observe(viewLifecycleOwner){liveMatches->
            if (!liveMatches.isNullOrEmpty()){
                val adapter = LiveMatchesAdapter(liveMatches,this)
                binding.liveMatchesRecycler.adapter = adapter
            }else{
                binding.liveMatchesRecycler.visibility = View.GONE
                binding.txtNoLiveMatches.visibility = View.VISIBLE
            }
            binding.liveMatchesProgress.visibility = View.GONE
        }

        //observe matches date
        viewModel.matchesDate.observe(viewLifecycleOwner){matchesDate->
            viewModel.getMatchesOnDateSelected(matchesDate)
        }

        //calender icon clickListener
        binding.calenderIcon.setOnClickListener {
            viewModel.displayCalender(requireContext())
        }
    }


    override fun onCompetitionClicked(id: Int) {
        val date = LocalDate.now()
        val format = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        if (viewModel.getMatchDate().isEmpty()){
            viewModel.getMatchesByIdAndDate(from = date.format(format) , to=date.format(format) , id)
        }else{
            viewModel.getMatchesByIdAndDate(from = viewModel.getMatchDate() , to=viewModel.getMatchDate() , id)
        }
        viewModel.setCompetitionId(id)
        binding.progress.visibility = View.VISIBLE
        binding.todayMatchesRecycler.visibility = View.GONE
        binding.txtNoEventFound.visibility = View.GONE
    }


    override fun onMatchClicked(match: MatchesResponseItem) {
        findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToMatchDetailsFragment(match))
    }

}