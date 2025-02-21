package com.example.goalify.Data.Intefaces

import com.google.gson.JsonElement

interface ITopScorerRepo {
    suspend fun getTopScorer(action:String , league_id:Int): JsonElement
    suspend fun getPlayer(action: String,playerId:Long):JsonElement
}