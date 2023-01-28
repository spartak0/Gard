package ru.spartak.gard.data.network.dto

data class PerInput(
    val keyboardmouse: Stats,
    val gamepad: Stats,
    val touch: Stats
)