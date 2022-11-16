package ru.spartak.gard.ui.root_screen.main_screen.games_tab.detail_screen.stats_tab.models

sealed class StatTime(val text:String){
    object SevenDays: StatTime(text= "Last 7")
    object ThirtyDays: StatTime(text= "Last 30")
    object Lifetime: StatTime(text= "Lifetime")
}