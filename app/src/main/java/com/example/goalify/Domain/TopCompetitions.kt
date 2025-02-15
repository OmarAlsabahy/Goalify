package com.example.goalify.Domain

import com.example.goalify.Data.Enums.Competition
import com.example.goalify.Data.Intefaces.IHomeRepo
import com.example.goalify.Domain.Interfaces.ITopCompetitions

class TopCompetitions (private val repo: IHomeRepo):ITopCompetitions {
    override fun getCompetitions(): Array<Competition> {
        return repo.getCompetitions()
    }

}