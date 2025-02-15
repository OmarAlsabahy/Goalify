package com.example.goalify.Presentation.ViewModels

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.content.Context
import android.icu.util.Calendar
import android.widget.DatePicker
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.goalify.Data.Enums.Actions
import com.example.goalify.Data.Enums.Competition
import com.example.goalify.Data.Enums.LeaguesId
import com.example.goalify.Data.Models.MatchesResponseItem
import com.example.goalify.Domain.LiveMatchesDomain
import com.example.goalify.Domain.MatchesDomain
import com.example.goalify.Domain.TopCompetitions
import com.google.gson.Gson
import com.google.gson.JsonElement
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val topCompetitions: TopCompetitions,
                                        private val matchesDomain: MatchesDomain, private val liveMatchesDomain:LiveMatchesDomain) : ViewModel() {
     private val date = getTodayDate()
    private val _competitions = MutableLiveData<Array<Competition>>()
    val competitions : LiveData<Array<Competition>>
        get() = _competitions
    private val _matches = MutableLiveData<List<MatchesResponseItem>>()
    val matches : LiveData<List<MatchesResponseItem>>
        get() = _matches
    private val _matchesError = MutableLiveData<String>()
    val matchesError : LiveData<String>
        get() = _matchesError
    val _liveMatchesError = MutableLiveData<String>()
    val liveMatchesError : LiveData<String>
        get() = _liveMatchesError
    private val _liveMatches = MutableLiveData<List<MatchesResponseItem>>()
    val liveMatches:LiveData<List<MatchesResponseItem>>
        get() = _liveMatches
    private val _matchesDate = MutableLiveData<String>()
    val matchesDate : LiveData<String>
        get() = _matchesDate
    private val _competitionId = MutableLiveData<Int>()
    val competitionId : LiveData<Int>
        get() = _competitionId

    init {
        getCompetitions()
        setCompetitionId(LeaguesId.premierLeague.league_id)
        getMatchesByIdAndDate(date , date , LeaguesId.premierLeague.league_id)
        getLiveMatches(date , date)
    }

    private fun getLiveMatches(from: String, to: String) {
        viewModelScope.launch(Dispatchers.Main) {
            val resultList = ArrayList<JsonElement>()
            for (leagueId in LeaguesId.values()){
                val result = viewModelScope.async(Dispatchers.IO) {
                    liveMatchesDomain.getLiveMatches(Actions.get_events.name , from , to , leagueId.league_id)
                }
                val liveMatchesResult = result.await()
                resultList.add(liveMatchesResult)
            }
            validateLiveMatches(resultList)

        }
    }

    private fun validateLiveMatches(liveMatchResultList:ArrayList<JsonElement>) {
        val allMatches = ArrayList<MatchesResponseItem>()
        for (result in liveMatchResultList) {
            if (result.isJsonArray) {
                val matches =
                    Gson().fromJson(result, Array<MatchesResponseItem>::class.java).toList()
                allMatches.addAll(matches)
            }
        }
       setMatchesLive(allMatches)
    }

    private fun setMatchesLive(allMatches: java.util.ArrayList<MatchesResponseItem>) {
        if (!allMatches.isNullOrEmpty()){
            _liveMatches.value = allMatches
        }else{
            _liveMatchesError.value = "No Event Found"
        }
    }

    fun getCompetitions(){
        _competitions.value = topCompetitions.getCompetitions()
    }

    fun getMatchesByIdAndDate(from:String , to:String , leaguesId: Int){
        viewModelScope.launch(Dispatchers.Main) {
          val result = viewModelScope.async(Dispatchers.IO) {
              matchesDomain.getMatchesByIdAndDate(action = Actions.get_events.name , from = from ,
                  to= to , league_id = leaguesId)
          }
            val matchesResult = result.await()
            setMatches(matchesResult)
        }

    }

    private fun setMatches(matchesResult: JsonElement) {
        if (matchesResult.isJsonArray){
            _matches.value = Gson().fromJson(matchesResult , Array<MatchesResponseItem>::class.java).toList()
        }else{
            _matchesError.value = "No Event Found"
        }
    }
    private fun getTodayDate(): String {
        val date = LocalDate.now()
        val format = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        return date.format(format)
    }

   fun displayCalender(context:Context) {
        val calender = Calendar.getInstance()
        val year = calender.get(Calendar.YEAR)
        val month = calender.get(Calendar.MONTH)
        val day = calender.get(Calendar.DAY_OF_MONTH)
        displayDatePickerDialog(context , year,month,day)
    }

    fun setCompetitionId(id:Int){
        _competitionId.value = id
    }

    fun getMatchesOnDateSelected(date:String){

        getMatchesByIdAndDate(date , date , _competitionId.value!!)
    }

    private fun displayDatePickerDialog(context: Context,year: Int, month: Int, day: Int) {
        val dialog = DatePickerDialog(context , object: OnDateSetListener {
            override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
                _matchesDate.value = "$year-${month+1}-$dayOfMonth"
            }
        }, year , month , day )
        dialog.show()
    }

}