package com.example.goalify.Domain

import com.example.goalify.Data.Intefaces.IHomeRepo
import com.example.goalify.Domain.Interfaces.ILiveMatchesDomain
import com.google.gson.JsonElement
import javax.inject.Inject

class LiveMatchesDomain @Inject constructor(private val repo:IHomeRepo): ILiveMatchesDomain {
    override suspend fun getLiveMatches(
        action: String,
        from: String,
        to: String,
        league_id: Int
    ): JsonElement {
        return repo.getLiveMatches(action,from,to,league_id)
    }

}