package com.example.goalify.Domain

import com.example.goalify.Data.Enums.Competition
import com.example.goalify.Data.Intefaces.IHomeRepo
class TopCompetitions (private val repo: IHomeRepo) {
    fun getCompetitions(): Array<Competition> {
        return repo.getCompetitions()
    }

}