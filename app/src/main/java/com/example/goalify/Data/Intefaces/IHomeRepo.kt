package com.example.goalify.Data.Intefaces

import com.example.goalify.Data.Enums.Competition
import com.example.goalify.Data.Models.MatchesResponse
import com.google.gson.JsonElement

interface IHomeRepo {
    fun getCompetitions():Array<Competition>
    suspend fun getMatchesByIdAndDate(action:String , from:String , to:String , league_id:Int):JsonElement
    suspend fun getLiveMatches(action:String , from:String , to:String , league_id:Int):JsonElement
}