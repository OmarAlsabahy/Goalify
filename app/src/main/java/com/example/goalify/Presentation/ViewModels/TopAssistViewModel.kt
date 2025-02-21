package com.example.goalify.Presentation.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.goalify.Data.Models.PlayerModel
import com.example.goalify.Data.Models.TopScorerModel
import com.example.goalify.Data.Models.TopScorersWithImageModel
import com.example.goalify.Domain.TopAssistDomain
import com.google.gson.Gson
import com.google.gson.JsonElement
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TopAssistViewModel @Inject constructor(private val topAssistDomain : TopAssistDomain):ViewModel() {
    private val _topScorer = MutableLiveData<TopScorerModel>()
    private val _topAssistWithImage = MutableLiveData<List<TopScorersWithImageModel>>()
    val topAssistWithImage : LiveData<List<TopScorersWithImageModel>>
        get() = _topAssistWithImage

    fun getTopAssist(leagueId:Int){
        viewModelScope.launch(Dispatchers.Main) {
            val result = viewModelScope.async(Dispatchers.IO) {
                topAssistDomain.getTopAssist(leagueId)
            }
            setTopScorer(result.await())
            getPlayerImage(_topScorer.value!!)
        }
    }

    private fun setTopScorer(await: JsonElement) {
        if (await.isJsonArray){
            _topScorer.value = Gson().fromJson(await,TopScorerModel::class.java)
        }
    }

    private fun getPlayerImage(players:TopScorerModel){
        viewModelScope.launch(Dispatchers.Main) {
            val deferredResults = players.map { player ->
                viewModelScope.async(Dispatchers.IO) {
                    val playerResult = topAssistDomain.getPlayerImage(player.player_key)
                    if (checkPlayerImage(playerResult)){
                        val instantPlayer = getInstantPlayer(playerResult)
                        val instantPlayerImage = instantPlayer[0].player_image
                        TopScorersWithImageModel(player,instantPlayerImage)
                    }else{
                        null
                    }
                }
            }
            val results = deferredResults.awaitAll()
            sortPlayers(results.filterNotNull())

        }
    }

    private fun sortPlayers(players: List<TopScorersWithImageModel>) {
        _topAssistWithImage.value = players.sortedByDescending { it.topScorer.assists }
    }

    private fun getInstantPlayer(await: JsonElement): PlayerModel {
        val player = Gson().fromJson(await , PlayerModel::class.java)
        return player
    }

    private fun checkPlayerImage(await: JsonElement):Boolean {
        if (await.isJsonArray){
            return true
        }else{
            return false
        }
    }


}