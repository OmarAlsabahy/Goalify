package com.example.goalify.Data.Repositories

import com.example.goalify.Data.Api.ApiServices
import com.example.goalify.Data.Enums.Competition
import com.example.goalify.Data.Intefaces.IHomeRepo
import com.example.goalify.Data.Models.MatchesResponse
import com.google.gson.JsonElement
import javax.inject.Inject

class HomeRepo @Inject constructor(private val api:ApiServices) : IHomeRepo {
    override fun getCompetitions(): Array<Competition> {
        return Competition.values()
    }

    override suspend fun getMatchesByIdAndDate(
        action: String,
        from: String,
        to: String,
        league_id: Int,
        timeZone:String
    ): JsonElement {
        return api.getMatchesByLeagueIdAndDate(action , from , to , league_id,timeZone)
    }

    override suspend fun getLiveMatches(
        action: String,
        from: String,
        to: String,
        league_id: Int,
        timeZone: String
    ): JsonElement {
        return api.getLiveMatches(action=action , from=from , to=to , league_id = league_id , timeZone = timeZone)
    }


}