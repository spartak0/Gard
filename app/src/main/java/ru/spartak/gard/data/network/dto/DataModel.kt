package ru.spartak.gard.data.network.dto

data class DataModel (val account:AccountModel,val stats: StatsModel)

data class AccountModel (val id:String,val name:String)

data class StatsModel (val all:AllModel)

data class AllModel (val overall:OverallModel)

data class OverallModel (val score:Int)

