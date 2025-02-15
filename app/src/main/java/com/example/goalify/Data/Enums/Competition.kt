package com.example.goalify.Data.Enums

enum class Competition(val competitionName: String, val imageUrl: String){
     premierLeague ("PremierLeague" , "https://apiv3.apifootball.com/badges/logo_leagues/152_premier-league.png")
     ,laLiga ("LaLiga" , "https://apiv3.apifootball.com/badges/logo_leagues/302_la-liga.png")
   , bundesliga("Bundesliga" , "https://apiv3.apifootball.com/badges/logo_leagues/171_2-bundesliga.png")
    ,serieA ("SerieA" , "https://apiv3.apifootball.com/badges/logo_leagues/207_serie-a.png")
   ,ligue1("Ligue1" , "https://apiv3.apifootball.com/badges/logo_leagues/168_ligue-1.png"),
    uefa("Uefa ChampionsLeague" , "https://apiv3.apifootball.com/badges/logo_leagues/3_uefa-champions-league.png")
   ,egyptianLeague("EgyptianLeague" , "https://apiv3.apifootball.com/badges/logo_leagues/141_premier-league.png")

}