package com.example.goalify.Presentation.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.goalify.Presentation.ClickListeners.MatchDetailsSelectorsClickListener
import com.example.goalify.R
import com.example.goalify.databinding.MatchdetailsselectorItemBinding

class MatchDetailsSelectorsAdapter(private val selectors:List<String>
,private val listener:MatchDetailsSelectorsClickListener):RecyclerView.Adapter<MatchDetailsSelectorsAdapter.ViewHolder>() {
    private var currentPosition=0
    private var previousPosition = 0
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = MatchdetailsselectorItemBinding.inflate(LayoutInflater.from(parent.context) , parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        if (selectors.isNullOrEmpty()){
            return 0
        }else{
            return selectors.size
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(selectors[position],position)
        holder.itemView.setOnClickListener {
            holder.onItemClicked(selectors[position],position)
        }
    }

    inner class ViewHolder(private val binding:MatchdetailsselectorItemBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(selector: String,position: Int) {
            binding.selector.text = selector
            if (position==currentPosition){
                binding.constraintLayout.setBackgroundResource(R.drawable.gradient_color)
                binding.selector.setTextColor(binding.root.getResources().getColor(R.color.white , null))
            }else{
                binding.constraintLayout.setBackgroundResource(R.color.cardsColor)
                binding.selector.setTextColor(binding.root.getResources().getColor(R.color.cardNameColor , null))
            }
        }

        fun onItemClicked(selector:String,position: Int) {
            if (position!=currentPosition){
                previousPosition = currentPosition
                currentPosition = position
                notifyItemChanged(currentPosition)
                notifyItemChanged(previousPosition)
                listener.onSelectorClicked(selector)
            }
        }

    }
}