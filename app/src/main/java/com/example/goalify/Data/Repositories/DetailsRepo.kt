package com.example.goalify.Data.Repositories

import com.example.goalify.Data.Enums.MatchDetailsSelectors
import com.example.goalify.Data.Intefaces.IDetailsRepo

class DetailsRepo : IDetailsRepo {
    override fun getTopSelector(): List<String> {
        val selectors = mutableListOf<String>()
        for (selector in MatchDetailsSelectors.values())
        {
            selectors.add(selector.name)
        }
        return selectors
    }

}