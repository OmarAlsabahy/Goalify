package com.example.goalify.Domain

import com.example.goalify.Data.Models.Goalscorer

class MatchGoalScorerDomain {
    fun getHomeGoalScorer(goalScorer:List<Goalscorer>):List<Goalscorer>{
        val scorers = mutableListOf<Goalscorer>()
        for (scorer in goalScorer){
            if (scorer.home_scorer.isNotEmpty()){
                scorers.add(scorer)
            }

        }
        return scorers
    }

    fun getAwayGoalScorer(goalScorer:List<Goalscorer>):List<Goalscorer>{
        val scorers = mutableListOf<Goalscorer>()
        for (scorer in goalScorer){
            if (scorer.away_scorer.isNotEmpty()){
                scorers.add(scorer)
            }
        }

        return scorers
    }
}