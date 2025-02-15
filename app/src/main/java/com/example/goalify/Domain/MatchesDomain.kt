package com.example.goalify.Domain

import com.example.goalify.Data.Intefaces.IHomeRepo
import com.example.goalify.Data.Models.MatchesResponse
import com.example.goalify.Domain.Interfaces.IMatchesDomain
import com.google.gson.JsonElement
import javax.inject.Inject

class MatchesDomain @Inject constructor(private val repo:IHomeRepo):IMatchesDomain {
    override suspend fun getMatchesByIdAndDate(
        action: String,
        from: String,
        to: String,
        league_id: Int
    ): JsonElement {

        return repo.getMatchesByIdAndDate(action,from,to,league_id)
    }

}