package ru.spartak.gard.ui.details

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import ru.spartak.gard.ui.theme.Text50
import ru.spartak.gard.ui.theme.spacing

@Composable
fun LoadBtn(
    modifier: Modifier,
    text: String,
    loadState: Boolean,
    onClick: () -> Unit,
) {
    Box(modifier = modifier) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    MaterialTheme.colors.primary,
                    RoundedCornerShape(4.dp)
                )
                .clickable {
                    onClick()
                },
            contentAlignment = Alignment.Center,
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = text,
                    style = MaterialTheme.typography.body2.copy(fontWeight = FontWeight.Medium)
                )
                AnimatedVisibility(visible = loadState) {
                    Spacer(modifier = Modifier.width(MaterialTheme.spacing.extraSmall))
                    CircularProgressIndicator(
                        modifier = Modifier
                            .padding(start = MaterialTheme.spacing.extraSmall)
                            .size(20.dp),
                        strokeWidth = 2.dp,
                        color = Text50
                    )
                }
            }
        }
        if (loadState) Surface(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(4.dp))
                .alpha(0.5f), color = Color.Black
        ) {
        }
    }
}