package com.example.goalify.Domain.Interfaces

import com.google.gson.JsonElement

interface ILiveMatchesDomain {
    suspend fun getLiveMatches(action:String , from:String , to:String , league_id:Int): JsonElement
}