package com.example.goalify.Data.Models

class TopScorerModel : ArrayList<TopScorerModelItem>()

data class TopScorerModelItem(
    val assists: String,
    val fk_stage_key: String,
    val goals: String,
    val penalty_goals: String,
    val player_key: Long,
    val player_name: String,
    val player_place: String,
    val stage_name: String,
    val team_key: String,
    val team_name: String
)