package com.example.goalify.Data.Models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

class MatchesResponse : ArrayList<MatchesResponseItem>()

@Parcelize
data class MatchesResponseItem(
    val cards: List<Card>,
    val country_id: String,
    val country_logo: String,
    val country_name: String,
    val fk_stage_key: String,
    val goalscorer: List<Goalscorer>,
    val league_id: String,
    val league_logo: String,
    val league_name: String,
    val league_year: String,
    val lineup: Lineup,
    val match_awayteam_extra_score: String,
    val match_awayteam_ft_score: String,
    val match_awayteam_halftime_score: String,
    val match_awayteam_id: String,
    val match_awayteam_name: String,
    val match_awayteam_penalty_score: String,
    val match_awayteam_score: String,
    val match_awayteam_system: String,
    val match_date: String,
    val match_hometeam_extra_score: String,
    val match_hometeam_ft_score: String,
    val match_hometeam_halftime_score: String,
    val match_hometeam_id: String,
    val match_hometeam_name: String,
    val match_hometeam_penalty_score: String,
    val match_hometeam_score: String,
    val match_hometeam_system: String,
    val match_id: String,
    val match_live: String,
    val match_referee: String,
    val match_round: String,
    val match_stadium: String,
    val match_status: String,
    val match_time: String,
    val stage_name: String,
    val statistics: List<Statistic>,
    val statistics_1half: List<Statistics1half>,
    val substitutions: Substitutions,
    val team_away_badge: String,
    val team_home_badge: String
):Parcelable

@Parcelize
data class Card(
    val away_fault: String,
    val away_player_id: String,
    val card: String,
    val home_fault: String,
    val home_player_id: String,
    val info: String,
    val score_info_time: String,
    val time: String
):Parcelable

@Parcelize
data class Goalscorer(
    val away_assist: String,
    val away_assist_id: String,
    val away_scorer: String,
    val away_scorer_id: String,
    val home_assist: String,
    val home_assist_id: String,
    val home_scorer: String,
    val home_scorer_id: String,
    val info: String,
    val score: String,
    val score_info_time: String,
    val time: String
):Parcelable

@Parcelize
data class Lineup(
    val away: Away,
    val home: Home
):Parcelable

@Parcelize
data class Statistic(
    val away: String,
    val home: String,
    val type: String
):Parcelable

@Parcelize
data class Statistics1half(
    val away: String,
    val home: String,
    val type: String
):Parcelable

@Parcelize
data class Substitutions(
    val away: List<AwayX>,
    val home: List<HomeX>
):Parcelable

@Parcelize
data class Away(
    val coach: List<Coach>,
    val starting_lineups: List<StartingLineup>,
    val substitutes: List<Substitute>
):Parcelable

@Parcelize
data class Home(
    val coach: List<Coach>,
    val starting_lineups: List<StartingLineup>,
    val substitutes: List<Substitute>
):Parcelable

@Parcelize
data class Coach(
    val lineup_number: String,
    val lineup_player: String,
    val lineup_position: String,
    val player_key: String
):Parcelable

@Parcelize
data class StartingLineup(
    val lineup_number: String,
    val lineup_player: String,
    val lineup_position: String,
    val player_key: String
):Parcelable

@Parcelize
data class Substitute(
    val lineup_number: String,
    val lineup_player: String,
    val lineup_position: String,
    val player_key: String
):Parcelable

@Parcelize
data class AwayX(
    val substitution: String,
    val substitution_player_id: String,
    val time: String
):Parcelable

@Parcelize
data class HomeX(
    val substitution: String,
    val substitution_player_id: String,
    val time: String
):Parcelable