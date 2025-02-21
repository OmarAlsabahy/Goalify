package com.example.goalify.Domain

import com.example.goalify.Data.Enums.Actions
import com.example.goalify.Data.Enums.LeaguesId
import com.example.goalify.Data.Intefaces.ITopScorerRepo
import com.google.gson.JsonElement
import javax.inject.Inject

class TopScorerDomain @Inject constructor(private val repo:ITopScorerRepo) {
    suspend fun getTopScorer(leaguesId: Int):JsonElement{
        return repo.getTopScorer(Actions.get_topscorers.name , leaguesId)
    }

    suspend fun getPlayerImage(playerId:Long):JsonElement{
        return repo.getPlayer(Actions.get_players.name , playerId)
    }
}