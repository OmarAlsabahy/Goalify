package com.example.goalify.Presentation.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.goalify.Data.Models.Goalscorer
import com.example.goalify.databinding.GoalScorerItemBinding

class GoalScorerAdapter(private val homeScorers: List<Goalscorer>, private val awayScorer:List<Goalscorer>):RecyclerView.Adapter<GoalScorerAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = GoalScorerItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        if (homeScorers.size>=awayScorer.size){
            return homeScorers.size
        }else if (awayScorer.size>homeScorers.size){
            return awayScorer.size
        }else{
            return 0
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val homeItem = if (position<homeScorers.size)homeScorers[position]else null
        val awayItem = if (position<awayScorer.size)awayScorer[position]else null
        holder.bind(homeItem,awayItem)
    }

    inner class ViewHolder(private val binding:GoalScorerItemBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(homeItem: Goalscorer?, awayItem: Goalscorer?) {
            binding.homeScorer.text = homeItem?.home_scorer
            if (!homeItem?.home_assist.isNullOrEmpty()){
                binding.homeAssist.text = "Assist : ${homeItem?.home_assist}"
            }
            binding.awayScorer.text = awayItem?.away_scorer
            if (!awayItem?.away_assist.isNullOrEmpty()){
                binding.awayAssist.text = "Assist : ${awayItem?.away_assist}"
            }

        }

    }
}