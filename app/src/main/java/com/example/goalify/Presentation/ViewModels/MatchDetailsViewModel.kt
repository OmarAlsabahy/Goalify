package com.example.goalify.Presentation.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.goalify.Data.Models.MatchesResponseItem
import com.example.goalify.Domain.MatchDetailsSelectorDomain
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MatchDetailsViewModel @Inject constructor(private val selectorsDomain:MatchDetailsSelectorDomain):ViewModel() {
    private val _match = MutableLiveData<MatchesResponseItem>()
    val match : LiveData<MatchesResponseItem>
        get() = _match
    private val _selectors = MutableLiveData<List<String>>()
    val selectors : LiveData<List<String>>
        get() = _selectors

    init {
        getSelectors()
    }
    fun setMatch(match:MatchesResponseItem){
        _match.value = match
    }

    fun getSelectors(){
        _selectors.value = selectorsDomain.getTopSelectors()
    }
}