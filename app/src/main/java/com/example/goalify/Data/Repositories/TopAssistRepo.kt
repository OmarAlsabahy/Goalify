package com.example.goalify.Data.Repositories

import com.example.goalify.Data.Api.ApiServices
import com.example.goalify.Data.Intefaces.ITopAssistRepo
import com.google.gson.JsonElement
import javax.inject.Inject

class TopAssistRepo @Inject constructor(private val api: ApiServices): ITopAssistRepo {
    override suspend fun getTopAssist(action: String, league_id: Int): JsonElement {
        return api.getTopAssist(action , league_id)
    }

    override suspend fun getPlayer(action: String, playerId: Long): JsonElement {
        return api.getPlayer(action,playerId)
    }

}