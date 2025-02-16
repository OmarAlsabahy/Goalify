package com.example.goalify.Presentation.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.goalify.Data.Models.Substitute
import com.example.goalify.databinding.LineupItemBinding

class SubstitutesAdapter(private val home:List<Substitute> , private val away:List<Substitute>) : RecyclerView.Adapter<SubstitutesAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = LineupItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        if (home.isNotEmpty() && away.isNotEmpty()){
            return home.size
        }else{
            return 0
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(home[position],away[position])
    }

    inner class ViewHolder(private val binding:LineupItemBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(home: Substitute, away: Substitute) {
            binding.homePlayerNumber.text = home.lineup_number
            binding.homePlayerName.text = home.lineup_player
            binding.awayPlayerNumber.text = away.lineup_number
            binding.awayPlayerName.text = away.lineup_player
        }

    }
}