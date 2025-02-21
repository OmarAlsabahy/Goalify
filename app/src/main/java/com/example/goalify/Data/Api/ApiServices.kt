package com.example.goalify.Data.Api

import com.example.goalify.Data.Models.MatchesResponse
import com.google.gson.JsonElement
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {
    @GET("/")
    suspend fun getMatchesByLeagueIdAndDate(
        @Query("action")action:String,
        @Query("from")from:String,
        @Query("to")to:String,
        @Query("league_id")league_id:Int,
        @Query("timezone")timeZone:String
    ): JsonElement

    @GET("/")
    suspend fun getLiveMatches(
        @Query("action")action:String,
        @Query("from")from:String,
        @Query("to")to:String,
        @Query("league_id")league_id:Int,
        @Query("match_live")live:Int = 1,
        @Query("timezone")timeZone:String
    ):JsonElement

    @GET("/")
    suspend fun getTopScorers(
        @Query("action")action: String,
        @Query("league_id")league_id: Int
    ):JsonElement

    @GET("/")
    suspend fun getTopAssist(
        @Query("action")action: String,
        @Query("league_id")league_id: Int
    ):JsonElement

    @GET("/")
    suspend fun getPlayer(
        @Query("action")action: String,
        @Query("player_id")playerId:Long
    ):JsonElement
}