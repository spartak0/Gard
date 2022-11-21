package ru.spartak.gard.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Resources
import android.os.Build.VERSION
import android.os.Build.VERSION_CODES
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


object StatusBarHeight {
    fun calculate(): Dp {
        return 38.dp
    }
}