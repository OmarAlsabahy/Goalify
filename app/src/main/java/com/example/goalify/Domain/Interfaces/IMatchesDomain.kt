package com.example.goalify.Domain.Interfaces

import com.example.goalify.Data.Models.MatchesResponse
import com.google.gson.JsonElement

interface IMatchesDomain {
    suspend fun getMatchesByIdAndDate(action:String , from:String , to:String , league_id:Int):JsonElement
}