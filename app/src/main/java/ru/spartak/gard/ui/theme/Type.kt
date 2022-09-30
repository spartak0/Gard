package ru.spartak.gard.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import ru.spartak.gard.R

val sfFontFamily = FontFamily(
    Font(R.font.sf_bold, weight = FontWeight.Bold),
    Font(R.font.sf_medium, weight = FontWeight.Medium),
    Font(R.font.sf_regular, weight = FontWeight.Normal),
    Font(R.font.sf_light, weight = FontWeight.Light),
)

// Set of Material typography styles to start with
private val defaultTypography = Typography()

@OptIn(ExperimentalTextApi::class)
val appTypography = Typography(
    h1 = defaultTypography.h1.copy(
        fontFamily = sfFontFamily,
        platformStyle = PlatformTextStyle(includeFontPadding = false),
        color = Text50
    ),
    h2 = defaultTypography.h2.copy(
        fontFamily = sfFontFamily,
        platformStyle = PlatformTextStyle(includeFontPadding = false),
        color = Text50
    ),
    h3 = defaultTypography.h3.copy(
        fontFamily = sfFontFamily,
        platformStyle = PlatformTextStyle(includeFontPadding = false),
        color = Text50
    ),
    h4 = defaultTypography.h4.copy(
        fontFamily = sfFontFamily,
        platformStyle = PlatformTextStyle(includeFontPadding = false),
        color = Text50
    ),
    h5 = defaultTypography.h5.copy(
        fontFamily = sfFontFamily,
        fontWeight = FontWeight.Bold,
        platformStyle = PlatformTextStyle(includeFontPadding = false),
        color = Text50
    ),
    h6 = defaultTypography.h6.copy(
        fontFamily = sfFontFamily,
        fontWeight = FontWeight.Bold,
        platformStyle = PlatformTextStyle(includeFontPadding = false),
        color = Text50
    ),
    subtitle1 = defaultTypography.subtitle1.copy(
        fontFamily = sfFontFamily,
        fontWeight= FontWeight.Bold,
        platformStyle = PlatformTextStyle(includeFontPadding = false),
        color = Text50
    ),
    subtitle2 = defaultTypography.subtitle2.copy(
        fontFamily = sfFontFamily,
        fontWeight = FontWeight.Bold,
        platformStyle = PlatformTextStyle(includeFontPadding = false),
        color = Text50
    ),
    body1 = defaultTypography.body1.copy(
        fontFamily = sfFontFamily,
        fontWeight = FontWeight.Bold,
        platformStyle = PlatformTextStyle(includeFontPadding = false),
        color = Text50
    ),
    body2 = defaultTypography.body2.copy(
        fontFamily = sfFontFamily,
        platformStyle = PlatformTextStyle(includeFontPadding = false),
        color = Text50
    ),
    button = defaultTypography.button.copy(
        fontFamily = sfFontFamily,
        platformStyle = PlatformTextStyle(includeFontPadding = false),
        color = Text50
    ),
    caption = defaultTypography.caption.copy(
        fontFamily = sfFontFamily,
        fontWeight = FontWeight.Medium,
        platformStyle = PlatformTextStyle(includeFontPadding = false),
        color = Text50
    ),
    overline = defaultTypography.overline.copy(
        fontFamily = sfFontFamily,
        platformStyle = PlatformTextStyle(includeFontPadding = false),
        color = Text50
    )
)
