package com.example.goalify.Presentation.ClickListeners

import com.example.goalify.Data.Models.MatchesResponseItem

interface MatchClickListener {
    fun onMatchClicked(match:MatchesResponseItem)
}