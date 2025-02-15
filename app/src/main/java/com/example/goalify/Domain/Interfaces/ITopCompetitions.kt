package com.example.goalify.Domain.Interfaces

import com.example.goalify.Data.Enums.Competition

interface ITopCompetitions {
    fun getCompetitions():Array<Competition>
}