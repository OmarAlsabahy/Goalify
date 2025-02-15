package com.example.goalify.Domain

import com.example.goalify.Data.Intefaces.IDetailsRepo
import javax.inject.Inject

class MatchDetailsSelectorDomain @Inject constructor(private val repo: IDetailsRepo) {
    fun getTopSelectors(): List<String> {
        return repo.getTopSelector()
    }

}