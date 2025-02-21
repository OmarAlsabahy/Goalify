package com.example.goalify.Data.Repositories

import com.example.goalify.Data.Api.ApiServices
import com.example.goalify.Data.Intefaces.ITopScorerRepo
import com.google.gson.JsonElement
import javax.inject.Inject

class TopScorerRepo @Inject constructor(private val api:ApiServices):ITopScorerRepo {
    override suspend fun getTopScorer(action: String, league_id: Int): JsonElement {
        return api.getTopScorers(action,league_id)
    }

    override suspend fun getPlayer(action: String, playerId: Long): JsonElement {
        return api.getPlayer(action,playerId)
    }
}