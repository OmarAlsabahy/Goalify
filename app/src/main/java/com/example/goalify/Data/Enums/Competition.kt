package com.example.goalify.Data.Enums

enum class Competition(val competitionName: String, val imageUrl: String){
     premierLeague ("PremierLeague" , "https://apiv3.apifootball.com/badges/logo_leagues/152_premier-league.png"),
    CommunityShield("CommunityShield", "https://apiv3.apifootball.com/badges/logo_leagues/377_community-shield.png"),
    FACup("FACup","https://apiv3.apifootball.com/badges/logo_leagues/146_fa-cup.png")
     ,laLiga ("LaLiga" , "https://apiv3.apifootball.com/badges/logo_leagues/302_la-liga.png")
    ,CopaDelRey("CopaDelRey" , "https://apiv3.apifootball.com/badges/logo_leagues/300_copa-del-rey.png")
   , bundesliga("Bundesliga" , "https://apiv3.apifootball.com/badges/logo_leagues/175_bundesliga.png")
    ,serieA ("SerieA" , "https://apiv3.apifootball.com/badges/logo_leagues/207_serie-a.png")
   ,ligue1("Ligue1" , "https://apiv3.apifootball.com/badges/logo_leagues/168_ligue-1.png"),
    uefa("Uefa ChampionsLeague" , "https://apiv3.apifootball.com/badges/logo_leagues/3_uefa-champions-league.png")
   ,egyptianLeague("EgyptianLeague" , "https://apiv3.apifootball.com/badges/logo_leagues/141_premier-league.png")

}