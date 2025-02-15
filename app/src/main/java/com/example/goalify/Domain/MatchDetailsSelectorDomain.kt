package com.example.goalify.Domain

import com.example.goalify.Data.Intefaces.IDetailsRepo
import com.example.goalify.Domain.Interfaces.IMatchDetailsSelectorsDomain
import javax.inject.Inject

class MatchDetailsSelectorDomain @Inject constructor(private val repo: IDetailsRepo):IMatchDetailsSelectorsDomain {
    override fun getTopSelectors(): List<String> {
        return repo.getTopSelector()
    }

}