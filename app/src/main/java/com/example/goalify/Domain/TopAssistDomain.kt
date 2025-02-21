package com.example.goalify.Domain

import com.example.goalify.Data.Enums.Actions
import com.example.goalify.Data.Intefaces.ITopAssistRepo
import com.google.gson.JsonElement
import javax.inject.Inject

class TopAssistDomain @Inject constructor(private val repo : ITopAssistRepo) {
    suspend fun getTopAssist(league_id: Int): JsonElement {
        return repo.getTopAssist(Actions.get_topscorers.name , league_id)
    }

    suspend fun getPlayerImage(playerId:Long):JsonElement{
        return repo.getPlayer(Actions.get_players.name , playerId)
    }
}