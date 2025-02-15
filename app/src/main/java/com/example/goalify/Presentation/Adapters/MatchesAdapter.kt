package com.example.goalify.Presentation.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.goalify.Data.Models.MatchesResponseItem
import com.example.goalify.Presentation.ClickListeners.MatchClickListener
import com.example.goalify.databinding.MatchesItemBinding

class MatchesAdapter(private val matches:List<MatchesResponseItem> , private val listener:MatchClickListener):RecyclerView.Adapter<MatchesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = MatchesItemBinding.inflate(LayoutInflater.from(parent.context) , parent , false)
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
        holder.itemView.setOnClickListener {
            listener.onMatchClicked(matches[position])
        }
    }

    inner class  ViewHolder(private val binding: MatchesItemBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(match: MatchesResponseItem) {
            binding.homeTeamName.text = match.match_hometeam_name
            binding.awayTeamName.text = match.match_awayteam_name
            binding.matchTime.text = match.match_time
            if (!match.match_awayteam_score.isNullOrEmpty() || !match.match_hometeam_score.isNullOrEmpty()){
                binding.matchScore.text = match.match_hometeam_score + " : " + match.match_awayteam_score
            }
            binding.matchStatue.text = match.match_status
            Glide.with(binding.root)
                .load(match.team_home_badge)
                .into(binding.homeTeamImage)
            Glide.with(binding.root)
                .load(match.team_away_badge)
                .into(binding.awayTeamImage)
        }

    }
}