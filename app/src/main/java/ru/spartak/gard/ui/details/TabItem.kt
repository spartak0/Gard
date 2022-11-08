package ru.spartak.gard.ui.details

import androidx.compose.runtime.Composable

interface TabItem{
    val title: String
    val content: @Composable () -> Unit
}