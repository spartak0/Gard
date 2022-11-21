package ru.spartak.gard.ui.root_screen.main_screen.home_tab.profile_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.spartak.gard.R
import ru.spartak.gard.ui.theme.*

@Composable
fun RefferalsCard(refferalCode: String, onCopied:()->Unit,) {
    val clipboardManager: ClipboardManager = LocalClipboardManager.current
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(4.dp)),
        color = MaterialTheme.colors.secondary
    ) {
        Column(modifier = Modifier.padding(horizontal = MaterialTheme.spacing.medium)) {
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
            SubtitleRefferalCard()
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.smallMedium))
            InfoTextRefferalCard()
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
            Text(
                text = stringResource(R.string.your_refferal_code),
                style = MaterialTheme.typography.caption,
                color = Text500,
            )
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraSmall))
            Row(modifier = Modifier.height(40.dp)) {
                RefferalCode(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(1f),
                    refferalCode = refferalCode
                )
                Spacer(modifier = Modifier.width(8.dp))
                CopyBtn(modifier = Modifier
                    .fillMaxHeight()
                    .width(120.dp), onClick = {
                    clipboardManager.setText(AnnotatedString(refferalCode))
                    onCopied()
                })
            }
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
        }
    }
}

@Composable
fun SubtitleRefferalCard() {
    Text(
        text = stringResource(R.string.gard_referrals),
        style = MaterialTheme.typography.subtitle1
    )
}

@Composable
fun InfoTextRefferalCard() {
    Text(buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                fontFamily = sfFontFamily,
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                letterSpacing = 0.25.sp
            )
        ) {
            append(stringResource(R.string.info_refferal_card_1))
        }
        withStyle(
            style = SpanStyle(
                fontFamily = sfFontFamily,
                fontSize = 14.sp,
                color = Tertiary500,
                fontWeight = FontWeight.Normal,
                letterSpacing = 0.25.sp
            )
        ) {
            append(stringResource(R.string.info_refferal_card_2))
        }
        withStyle(
            style = SpanStyle(
                fontFamily = sfFontFamily,
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                letterSpacing = 0.25.sp
            )
        ) {
            append(stringResource(R.string.info_refferal_card_3))
        }
    })
}

@Composable
fun RefferalCode(modifier: Modifier, refferalCode: String) {
    Box(
        modifier = modifier
            .border(width = 1.dp, color = Muted700, shape = RoundedCornerShape(4.dp))
            .background(color = Muted900, shape = RoundedCornerShape(4.dp)),
        contentAlignment = Alignment.CenterStart

    ) {
        Text(
            text = refferalCode,
            style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.Normal),
            color = Text500,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = MaterialTheme.spacing.smallMedium)
        )
    }
}

@Composable
fun CopyBtn(modifier: Modifier, onClick: () -> Unit) {
    Box(
        modifier = modifier
            .background(
                color = MaterialTheme.colors.primary,
                shape = RoundedCornerShape(4.dp)
            )
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Row {
            Icon(
                painter = painterResource(id = R.drawable.ic_copy),
                contentDescription = null,
                tint = Muted100
            )
            Spacer(modifier = Modifier.width(MaterialTheme.spacing.extraSmall))
            Text(text = "Copy", style = MaterialTheme.typography.body2)
        }
    }
}
