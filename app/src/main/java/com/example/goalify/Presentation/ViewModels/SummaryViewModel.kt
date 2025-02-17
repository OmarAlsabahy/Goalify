package com.example.goalify.Presentation.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.goalify.Data.Models.Goalscorer
import com.example.goalify.Data.Models.MatchesResponseItem
import com.example.goalify.Domain.MatchGoalScorerDomain
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SummaryViewModel @Inject constructor(private val matchGoalscorer: MatchGoalScorerDomain):ViewModel() {

    private val _homeScorer = MutableLiveData<List<Goalscorer>>()
    val homeScorer : LiveData<List<Goalscorer>>
        get() = _homeScorer
    private val _awayScorer = MutableLiveData<List<Goalscorer>>()
    val awayScorer : LiveData<List<Goalscorer>>
        get() = _awayScorer

    fun setMatch(match:MatchesResponseItem){
        _homeScorer.value = matchGoalscorer.getHomeGoalScorer(match.goalscorer)
        _awayScorer.value = matchGoalscorer.getAwayGoalScorer(match.goalscorer)
    }


}