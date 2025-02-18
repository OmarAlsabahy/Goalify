package com.example.goalify.Presentation.Adapters

import android.view.LayoutInflater
import android.view.View
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
        val homeItem = if (position<home.size)home[position]else HomeX("","" , "")
        val awayItem = if (position<away.size)away[position]else AwayX("" , "" , "")
        holder.bind(homeItem,awayItem)
    }

    inner class ViewHolder(private val binding:SubstituionsItemBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(home: HomeX, away: AwayX) {
           if (home.substitution.isNotEmpty() && away.substitution.isNotEmpty()){
               binding.homeOutPlayerName.text = home.substitution.split("|")[0]
               binding.homeInPlayerName.text = home.substitution.split("|")[1]
               binding.awayOutPlayerName.text = away.substitution.split("|")[0]
               binding.awayInPlayerName.text = away.substitution.split("|")[1]
               binding.homeTime.text = home.time
               binding.awayTime.text = away.time
           }else if(home.substitution.isNotEmpty() && away.substitution.isEmpty()){
               binding.homeOutPlayerName.text = home.substitution.split("|")[0]
               binding.homeInPlayerName.text = home.substitution.split("|")[1]
               binding.homeTime.text = home.time
               binding.awayInArrow.visibility = View.GONE
               binding.awayOutArrow.visibility = View.GONE
               binding.awayOutPlayerName.visibility = View.GONE
               binding.awayInPlayerName.visibility = View.GONE
               binding.awayTime.visibility = View.INVISIBLE
           }else if(home.substitution.isEmpty() && away.substitution.isNotEmpty()){
               binding.awayOutPlayerName.text = away.substitution.split("|")[0]
               binding.awayInPlayerName.text = away.substitution.split("|")[1]
               binding.awayTime.text = away.time
               binding.homeInArrow.visibility = View.GONE
               binding.homeOutArrow.visibility = View.GONE
               binding.homeOutPlayerName.visibility = View.GONE
               binding.homeInPlayerName.visibility = View.GONE
               binding.homeTime.visibility = View.INVISIBLE
           }else{
               binding.homeInArrow.visibility = View.GONE
               binding.homeOutArrow.visibility = View.GONE
               binding.awayInArrow.visibility = View.GONE
               binding.awayOutArrow.visibility = View.GONE
           }
        }

    }
}