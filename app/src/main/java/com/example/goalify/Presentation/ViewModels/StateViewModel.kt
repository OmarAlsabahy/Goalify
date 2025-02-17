package com.example.goalify.Presentation.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.goalify.Domain.StateDomain
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class StateViewModel @Inject constructor(private val stateDomain : StateDomain) : ViewModel() {
    private val _homeShotsOnTarget = MutableLiveData<Int>()
    val homeShotsOnTarget : LiveData<Int>
        get() = _homeShotsOnTarget
    private val _awayShotsOnTarget = MutableLiveData<Int>()
    val awayShotsOnTarget : LiveData<Int>
        get() = _awayShotsOnTarget
    private val _homeTotalShots = MutableLiveData<Int>()
    val homeTotalShots : LiveData<Int>
        get() = _homeTotalShots
    private val _awayTotalShots = MutableLiveData<Int>()
    val awayTotalShots : LiveData<Int>
        get() = _awayTotalShots
    private val _homePossession = MutableLiveData<String>()
    val homePossession : LiveData<String>
        get() = _homePossession
    private val _awayPossession = MutableLiveData<String>()
    val awayPossession : LiveData<String>
        get() = _awayPossession
    private val _homeYellowCards = MutableLiveData<String>()
    val homeYellowCards : LiveData<String>
        get() = _homeYellowCards
    private val _awayYellowCards = MutableLiveData<String>()
    val awayYellowCards : LiveData<String>
        get() = _awayYellowCards
    private val _homeRedCards = MutableLiveData<String>()
    val homeRedCards : LiveData<String>
        get() = _homeRedCards
    private val _awayRedCards = MutableLiveData<String>()
    val awayRedCards : LiveData<String>
        get() = _awayRedCards

    private val _homeCorners = MutableLiveData<Int>()
    val homeCorners : LiveData<Int>
        get() = _homeCorners
    private val _awayCorners = MutableLiveData<Int>()
    val awayCorners : LiveData<Int>
        get() = _awayCorners
    private val _homeFouls = MutableLiveData<String>()
    val homeFouls : LiveData<String>
        get() = _homeFouls
    private val _awayFouls = MutableLiveData<String>()
    val awayFouls : LiveData<String>
        get() = _awayFouls

    private val _homePenalties = MutableLiveData<Int>()
    val homePenalties : LiveData<Int>
        get() = _homePenalties
    private val _awayPenalties = MutableLiveData<Int>()
    val awayPenalties : LiveData<Int>
        get() = _awayPenalties
    private val _homeOffsides = MutableLiveData<String>()
    val  homeOffsides : LiveData<String>
        get() = _homeOffsides
    private val _awayOffsides = MutableLiveData<String>()
    val  awayOffsides : LiveData<String>
        get() = _awayOffsides

    fun getHomeShotsOnTarget(shots:MutableList<String>){
        _homeShotsOnTarget.value = stateDomain.getHomeShotsOnTarget(shots)
    }

    fun getAwayShotsOnTarget(shots:MutableList<String>){
        _awayShotsOnTarget.value = stateDomain.getAwayShotsOnTarget(shots)
    }

    fun setTotalShots(homeShots:Int,awayShots:Int){
        _homeTotalShots.value = homeShots
        _awayTotalShots.value = awayShots
    }
    fun setBallPossession(home:String , away:String){
        _homePossession.value = home
        _awayPossession.value = away
    }
    fun setYellowCards(home:String,away: String){
        _homeYellowCards.value = home
        _awayYellowCards.value = away
    }
    fun setRedCards(home:String,away: String){
        _homeRedCards.value = home
        _awayRedCards.value = away
    }

    fun getHomeCorners(corners:MutableList<String>){

        _homeCorners.value = stateDomain.getHomeCorners(corners)
    }

    fun getAwayCorners(corners:MutableList<String>){

        _awayCorners.value = stateDomain.getAwayCorners(corners)
    }

    fun setFouls(home:String,away: String){
        _homeFouls.value = home
        _awayFouls.value = away

    }

    fun getHomePenalties(penalties:MutableList<String>){

        _homePenalties.value = stateDomain.getHomePenalties(penalties)

    }


    fun getAwayPenalties(penalties: MutableList<String>){

        _awayPenalties.value = stateDomain.getAwayPenalties(penalties)
    }

    fun setOffsides(home:String,away: String){
        _homeOffsides.value = home
        _awayOffsides.value = away
    }
}