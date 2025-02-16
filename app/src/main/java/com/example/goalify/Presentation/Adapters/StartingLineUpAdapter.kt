package com.example.goalify.Presentation.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.goalify.Data.Models.StartingLineup
import com.example.goalify.databinding.LineupItemBinding

class StartingLineUpAdapter(private val homeStartingLineUp: List<StartingLineup>,
                            private val awayStartingLineUp: List<StartingLineup>):RecyclerView.Adapter<StartingLineUpAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = LineupItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        if (homeStartingLineUp.isNotEmpty() && awayStartingLineUp.isNotEmpty()){
            return homeStartingLineUp.size
        }else{
            return 0
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(homeStartingLineUp[position],awayStartingLineUp[position])
    }

    inner class ViewHolder(private val binding:LineupItemBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(home: StartingLineup, away: StartingLineup) {
            binding.homePlayerNumber.text = home.lineup_number
            binding.homePlayerName.text = home.lineup_player
            binding.awayPlayerNumber.text = away.lineup_number
            binding.awayPlayerName.text = away.lineup_player
        }

    }
}