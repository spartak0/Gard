package ru.spartak.gard.ui.details

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ru.spartak.gard.ui.theme.White

@Composable
fun <T> BtnGroup(
    selected: MutableState<T>,
    list: List<T>,
    modifier: Modifier,
    content: @Composable BoxScope.(T) -> Unit
) {
    Row(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = modifier) {
        for (platform in list) {
            val border = if (selected.value == platform) BorderStroke(
                0.dp,
                Color.Transparent
            ) else BorderStroke(1.dp, White)
            val background =
                animateColorAsState(if (selected.value == platform) MaterialTheme.colors.primary else Color.Transparent)
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(4.dp))
                    .border(border, RoundedCornerShape(4.dp))
                    .background(background.value)
                    .weight(1f)
                    .fillMaxHeight()
                    .clickable {
                        selected.value = platform
                    },
                contentAlignment = Alignment.Center
            ) {
                content(platform)
            }
        }
    }
}