package com.example.goalify.Presentation.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.goalify.Data.Models.TopScorersWithImageModel
import com.example.goalify.databinding.TopScorerAssistItemBinding

class TopAssistAdapter(private val topScorers:List<TopScorersWithImageModel>):RecyclerView.Adapter<TopAssistAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = TopScorerAssistItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        if (topScorers.isNullOrEmpty()){
            return 0
        }else{
            return topScorers.size
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(topScorers[position])
    }

    inner class ViewHolder(private val binding: TopScorerAssistItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(topScorerModelItem: TopScorersWithImageModel) {
            binding.playerName.text = topScorerModelItem.topScorer.player_name
            binding.teamName.text = topScorerModelItem.topScorer.team_name
            binding.playerGoals.text = topScorerModelItem.topScorer.assists
            Glide.with(binding.root)
                .load(topScorerModelItem.imageUrl)
                .into(binding.playerImage)
        }

    }
}