package ru.spartak.gard.ui.details

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import ru.spartak.gard.ui.theme.Text50
import ru.spartak.gard.ui.theme.spacing

@Composable
fun CustomBtn(
    text: String,
    icon: Painter? = null,
    color: Color = MaterialTheme.colors.primary,
    iconPosition: IconPosition = IconPosition.END,
    modifier: Modifier,
    onClick: () -> Unit
) {
    Surface(
        color = color,
        modifier = modifier
            .clip(RoundedCornerShape(4.dp))
            .clickable {
                onClick()
            },
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (icon != null && iconPosition == IconPosition.START)
                Row {
                    Icon(
                        painter = icon,
                        contentDescription = null,
                        tint = Text50,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(MaterialTheme.spacing.extraSmall))
                }
            Text(
                text = text,
                style = MaterialTheme.typography.body2.copy(fontWeight = FontWeight.Medium)
            )

            if (icon != null && iconPosition == IconPosition.END)
                Row {
                    Spacer(modifier = Modifier.width(MaterialTheme.spacing.extraSmall))
                    Icon(
                        painter = icon,
                        contentDescription = null,
                        tint = Text50,
                        modifier = Modifier.size(16.dp)
                    )
                }
        }
    }

}

enum class IconPosition {
    START, END
}