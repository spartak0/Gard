package ru.spartak.gard.domain.model

data class PerInputModel(
    val keyboardmouse: StatsModel,
    val gamepad: StatsModel,
    val touch: StatsModel
)