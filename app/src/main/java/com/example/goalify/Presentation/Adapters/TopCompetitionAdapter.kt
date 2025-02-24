package com.example.goalify.Presentation.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.goalify.Data.Enums.Competition
import com.example.goalify.Data.Enums.LeaguesId
import com.example.goalify.Presentation.ClickListeners.TopCompetitionClickListener
import com.example.goalify.R
import com.example.goalify.databinding.CompetitionItemBinding

class TopCompetitionAdapter(private val competitions:Array<Competition> , private val listener: TopCompetitionClickListener):RecyclerView.Adapter<TopCompetitionAdapter.ViewHolder>() {
    var CurrentPosition = 0
    var previousPosition= 0
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val binding = CompetitionItemBinding.inflate(LayoutInflater.from(parent.context) , parent , false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
       if(competitions.isNullOrEmpty()){
           return 0
       }else{
           return competitions.size
       }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(competitions[position] , position)
        holder.itemView.setOnClickListener {
            holder.itemClicked(position,competitions[position].competitionName)
        }
    }

    inner class ViewHolder(private val binding:CompetitionItemBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(competition: Competition, position: Int) {
            binding.competitionName.setText(competition.competitionName)
            Glide.with(binding.root)
                .load(competition.imageUrl)
                .into(binding.competitionImage)
            if (position==CurrentPosition){
                binding.constraintLayout.setBackgroundResource(R.drawable.gradient_color)
                binding.competitionName.setTextColor(binding.root.resources.getColor(R.color.white , null))
            }else{
                binding.constraintLayout.setBackgroundResource(R.color.cardsColor)
                binding.competitionName.setTextColor(binding.root.resources.getColor(R.color.cardNameColor , null))
            }
        }

        fun itemClicked(position: Int , competitionName:String) {
           if (position!=CurrentPosition){
               previousPosition = CurrentPosition
               CurrentPosition = position
               notifyItemChanged(previousPosition)
               notifyItemChanged(CurrentPosition)
               changeData(competitionName)
           }

        }

        private fun changeData(competitionName: String) {
            when(competitionName){
                Competition.uefa.competitionName-> listener.onCompetitionClicked(LeaguesId.uefa.league_id)
                Competition.laLiga.competitionName ->listener.onCompetitionClicked(LeaguesId.laLiga.league_id)
                Competition.bundesliga.competitionName->listener.onCompetitionClicked(LeaguesId.bundesliga.league_id)
                Competition.premierLeague.competitionName -> listener.onCompetitionClicked(LeaguesId.premierLeague.league_id)
                Competition.serieA.competitionName -> listener.onCompetitionClicked(LeaguesId.seriaA.league_id)
                Competition.ligue1.competitionName -> listener.onCompetitionClicked(LeaguesId.ligue1.league_id)
                Competition.egyptianLeague.competitionName -> listener.onCompetitionClicked(LeaguesId.egyptianLeague.league_id)
                Competition.CopaDelRey.competitionName->listener.onCompetitionClicked(LeaguesId.CopaDelRey.league_id)
                Competition.CommunityShield.competitionName->listener.onCompetitionClicked(LeaguesId.CommunityShield.league_id)
                Competition.FACup.competitionName->listener.onCompetitionClicked(LeaguesId.FACup.league_id)
            }
        }


    }
}