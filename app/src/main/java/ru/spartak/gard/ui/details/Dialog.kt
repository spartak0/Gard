package ru.spartak.gard.ui.details

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.layout
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import ru.spartak.gard.ui.theme.Error600
import ru.spartak.gard.ui.theme.Muted700
import ru.spartak.gard.ui.theme.spacing

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun Dialog(
    subtitle: String,
    text: String,
    confirmText: String,
    rejectText: String,
    showDialog: MutableState<Boolean>,
    modifier: Modifier,
    onClick: () -> Unit
) {
    Dialog(
        onDismissRequest = { showDialog.value = false },
        properties = DialogProperties(usePlatformDefaultWidth = false),
    ) {
        Column(
            modifier = modifier
                .background(MaterialTheme.colors.secondary, RoundedCornerShape(4.dp)),
            horizontalAlignment = Alignment.End,
        ) {
            Text(
                text = subtitle,
                style = MaterialTheme.typography.subtitle1,
                modifier = Modifier
                    .padding(MaterialTheme.spacing.medium)
                    .fillMaxWidth()
            )
            Divider(modifier = Modifier.fillMaxWidth(), color = Muted700, thickness = 1.dp)
            Text(
                text = text,
                style = MaterialTheme.typography.body2,
                modifier = Modifier
                    .padding(MaterialTheme.spacing.medium)
                    .fillMaxWidth()
            )
            Divider(modifier = Modifier.fillMaxWidth(), color = Muted700, thickness = 1.dp)
            Row(modifier = Modifier.padding(MaterialTheme.spacing.medium)) {
                Box(
                    modifier = Modifier
                        .height(41.dp)
                        .border(1.dp, Muted700, RoundedCornerShape(4.dp))
                        .clip(RoundedCornerShape(4.dp))
                        .background(
                            color = MaterialTheme.colors.secondary
                        )
                        .clickable {
                            showDialog.value = false
                        }, contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = rejectText,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(horizontal = MaterialTheme.spacing.smallMedium),
                        style = MaterialTheme.typography.body2.copy(fontWeight = FontWeight.Medium)
                    )
                }
                Spacer(modifier = Modifier.width(MaterialTheme.spacing.medium))
                Box(
                    modifier = Modifier
                        .height(41.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .background(Error600)
                        .clickable { onClick() },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = confirmText,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(horizontal = MaterialTheme.spacing.smallMedium),
                        style = MaterialTheme.typography.body2.copy(fontWeight = FontWeight.Medium)
                    )
                }
            }

        }
    }
}

fun Modifier.bottomAlign() = layout { measurable, constraints ->
    val placeable = measurable.measure(constraints);
    layout(constraints.maxWidth, constraints.maxHeight) {
        placeable.place(0, constraints.maxHeight - placeable.height, 10f)
    }
}