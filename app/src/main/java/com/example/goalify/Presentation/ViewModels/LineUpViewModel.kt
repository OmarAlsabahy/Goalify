package com.example.goalify.Presentation.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.goalify.Data.Models.MatchesResponseItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LineUpViewModel @Inject constructor():ViewModel() {
    private val _match = MutableLiveData<MatchesResponseItem>()
    val match : LiveData<MatchesResponseItem>
        get() = _match

    fun setMatchData(match: MatchesResponseItem){
        _match.value = match
    }
}