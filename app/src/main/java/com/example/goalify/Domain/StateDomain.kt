package com.example.goalify.Domain

class StateDomain {
    fun getHomeShotsOnTarget(shots:MutableList<String>):Int{
        var sum = 0
        for (shot in shots){
            sum = sum+shot.toInt()
        }
        return sum
    }

    fun getAwayShotsOnTarget(shots:MutableList<String>):Int{
        var sum = 0
        for (shot in shots){
            sum = sum+shot.toInt()
        }
        return sum
    }

    fun getHomeCorners(corners:MutableList<String>):Int{
        var sum = 0
        for (corner in corners){
            sum = sum+corner.toInt()
        }
        return sum
    }

    fun getAwayCorners(corners:MutableList<String>):Int{
        var sum = 0
        for (corner in corners){
            sum = sum+corner.toInt()
        }
        return sum
    }

    fun getHomePenalties(penalties:MutableList<String>):Int{
        var sum = 0
        for (penalty in penalties){
            sum = sum+penalty.toInt()
        }
        return sum

    }

    fun getAwayPenalties(penalties: MutableList<String>):Int{
        var sum = 0
        for (penalty in penalties){
            sum = sum+penalty.toInt()
        }
        return sum
    }
}