package com.example.goalify.Presentation.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.goalify.Data.Models.MatchesResponseItem
import com.example.goalify.databinding.LivematchItemBinding

class LiveMatchesAdapter(private val matches: List<MatchesResponseItem>):RecyclerView.Adapter<LiveMatchesAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = LivematchItemBinding.inflate(LayoutInflater.from(parent.context) , parent , false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        if (matches.isNullOrEmpty()){
            return 0
        }else{
            return matches.size
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(matches[position])
    }

    inner class ViewHolder(private val binding: LivematchItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(match: MatchesResponseItem) {
            if (match.match_round.isNotEmpty()){
                binding.matchRound.text = "Round: " + match.match_round
            }
            binding.matchScore.text = match.match_hometeam_score + " : " + match.match_awayteam_score
            binding.competitionName.text = match.league_name
            binding.matchTime.text = match.match_time
            Glide.with(binding.root)
                .load(match.team_home_badge)
                .into(binding.homeTeamImage)
            Glide.with(binding.root)
                .load(match.team_away_badge)
                .into(binding.awayTeamImage)
        }

    }
}