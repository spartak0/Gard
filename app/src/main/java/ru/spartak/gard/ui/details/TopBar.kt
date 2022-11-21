package ru.spartak.gard.ui.details

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ru.spartak.gard.R
import ru.spartak.gard.ui.theme.spacing


@Composable
fun TopBar(
    subtitleText: String,
    modifier: Modifier = Modifier,
    leftView: @Composable (RowScope.() -> Unit)? = null,
    rightView: @Composable (RowScope.() -> Unit)? = null,
) {
    Box(
        modifier = modifier
    ) {
        Text(
            text = subtitleText,
            style = MaterialTheme.typography.h6,
            modifier = Modifier.align(Alignment.Center)
        )
        leftView?.let {
            Row(modifier = Modifier.align(Alignment.CenterStart)) {
                it()
            }
        }
        rightView?.let {
            Row(modifier = Modifier.align(Alignment.CenterEnd)) { it() }
        }

    }
}

@Composable
fun BackBtn(contentColor: Color = MaterialTheme.colors.primary, onClick: () -> Unit) {
    TopAppBarView(
        icon = painterResource(id = R.drawable.ic_arrow_back),
        text = stringResource(id = R.string.back),
        leftMargin = MaterialTheme.spacing.smallMedium,
        rightMargin = MaterialTheme.spacing.large,
        contentColor = contentColor,
        modifier = Modifier
            .fillMaxHeight()
            .clip(RoundedCornerShape(4.dp))
    ) {
        onClick()
    }
}

@Composable
fun EditBtn(onClick: () -> Unit) {
    TopAppBarView(
        icon = painterResource(id = R.drawable.ic_edit),
        text = stringResource(R.string.edit),
        leftMargin = MaterialTheme.spacing.large,
        rightMargin = MaterialTheme.spacing.smallMedium,
        modifier = Modifier
            .fillMaxHeight()
            .clip(RoundedCornerShape(4.dp))
    ) {
        onClick()
    }
}

@Composable
fun TopAppBarView(
    icon: Painter,
    text: String,
    modifier: Modifier = Modifier,
    leftMargin: Dp,
    rightMargin: Dp,
    contentColor: Color = MaterialTheme.colors.primary,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
            .clickable {
                onClick()
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.width(leftMargin))
        Icon(
            painter = icon,
            contentDescription = null,
            tint = contentColor
        )
        Spacer(modifier = Modifier.width(MaterialTheme.spacing.extraSmall))
        Text(
            text = text,
            style = MaterialTheme.typography.body2.copy(fontWeight = FontWeight.Medium),
            color = contentColor
        )
        Spacer(modifier = Modifier.width(rightMargin))
    }
}
