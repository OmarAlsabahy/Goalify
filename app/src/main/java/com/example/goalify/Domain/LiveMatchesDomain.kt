package com.example.goalify.Domain

import android.icu.util.TimeZone
import com.example.goalify.Data.Intefaces.IHomeRepo
import com.google.gson.JsonElement
import javax.inject.Inject

class LiveMatchesDomain @Inject constructor(private val repo:IHomeRepo) {
     suspend fun getLiveMatches(
        action: String,
        from: String,
        to: String,
        league_id: Int
    ): JsonElement {
         val timeZone = TimeZone.getDefault().id
        return repo.getLiveMatches(action,from,to,league_id,timeZone)
    }

}