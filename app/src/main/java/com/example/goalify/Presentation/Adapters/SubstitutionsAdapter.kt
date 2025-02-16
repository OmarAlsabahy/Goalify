package com.example.goalify.Presentation.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.goalify.Data.Models.AwayX
import com.example.goalify.Data.Models.HomeX
import com.example.goalify.databinding.SubstituionsItemBinding

class SubstitutionsAdapter(private val home:List<HomeX> , private val away:List<AwayX>):RecyclerView.Adapter<SubstitutionsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = SubstituionsItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        if (home.isNotEmpty()&&away.isNotEmpty()){
            if (home.size>=away.size){
                return home.size
            }else{
                return away.size
            }
        }else if (home.isEmpty() && away.isNotEmpty()){
            return away.size
        }else if (home.isNotEmpty() && away.isEmpty()){
            return home.size
        }else{
            return 0
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val homeItem = if (position<home.size)home[position]else null
        val awayItem = if (position<away.size)away[position]else null
        holder.bind(homeItem!!,awayItem!!)
    }

    inner class ViewHolder(private val binding:SubstituionsItemBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(home: HomeX, away: AwayX) {
            val homeOut = home.substitution.split("|")[0]
            val homeIn = home.substitution.split("|")[1]
            val awayOut = away.substitution.split("|")[0]
            val awayIn = away.substitution.split("|")[1]
            binding.homeOutPlayerName.text = homeOut
            binding.homeInPlayerName.text = homeIn
            binding.awayOutPlayerName.text = awayOut
            binding.awayInPlayerName.text = awayIn
        }

    }
}