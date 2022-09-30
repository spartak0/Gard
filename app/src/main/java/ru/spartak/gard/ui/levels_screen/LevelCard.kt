package ru.spartak.gard.ui.levels_screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import ru.spartak.gard.R
import ru.spartak.gard.ui.theme.*

@Composable
fun LevelCard(level: Level, levelStatus: LevelStatus, number: String) {
    when (levelStatus) {
        LevelStatus.Current -> CurrentLevelCard(level = level, number = number)
        LevelStatus.Default -> DefaultLevelCard(level = level, number = number)
        LevelStatus.Completed -> CompletedLevelCard(level = level, number = number)
    }
}

@Composable
fun DefaultLevelCard(level: Level, number: String) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(112.dp)
            .clip(RoundedCornerShape(4.dp)),
        color = MaterialTheme.colors.secondary,
    ) {
        Row {
            ImageSideCard(
                number = number,
                numberBackgroundColor = Dark300,
                pictureId = level.imageId
            )
            InfoSideCard(level = level, levelStatus = LevelStatus.Default)
        }
    }
}

@Composable
fun CurrentLevelCard(level: Level, number: String) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(112.dp)
            .clip(RoundedCornerShape(4.dp)),
        color = MaterialTheme.colors.secondary,
        border = BorderStroke(2.dp, MaterialTheme.colors.primary)
    ) {
        Row {
            ImageSideCard(
                number = number,
                numberBackgroundColor = MaterialTheme.colors.primary,
                pictureId = level.imageId
            )
            InfoSideCard(level = level, levelStatus = LevelStatus.Current)
        }
    }
}

@Composable
fun CompletedLevelCard(level: Level, number: String) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(112.dp)
            .clip(RoundedCornerShape(4.dp)),
        color = MaterialTheme.colors.secondary,
    ) {
        Row {
            ImageSideCard(
                number = number,
                numberBackgroundColor = Dark300,
                pictureId = level.imageId
            )
            InfoSideCard(level = level, levelStatus = LevelStatus.Completed)
        }
    }
}

@Composable
fun InfoSideCard(level: Level, levelStatus: LevelStatus) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                start = MaterialTheme.spacing.smallMedium,
                end = MaterialTheme.spacing.medium
            )
    ) {
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            LevelName(levelName = level.name, dotNextName = levelStatus == LevelStatus.Current)
            if (levelStatus != LevelStatus.Default) {
                LevelStatusView(levelStatus = levelStatus)
            } else {
                UnlockPts(level.unlockPtsFirst, level.unlockPtsSecond)
            }
        }
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.smallMedium))
        Bonus(
            modifier = Modifier
                .fillMaxWidth()
                .height(18.dp),
            nameBonus = stringResource(id = R.string.starting_bonus),
            bonusValue = level.startingBonus.toString()
        )
        Spacer(modifier = Modifier.height(10.dp))
        Bonus(
            modifier = Modifier
                .fillMaxWidth()
                .height(18.dp),
            nameBonus = stringResource(R.string.task_bonus),
            bonusValue = "+${level.tasksBonus}"
        )
    }
}

@Composable
fun UnlockPts(firstValue: Int, secondValue: Int) {
    val first = ParserDecimal.pars(firstValue)
    val second = ParserDecimal.pars(secondValue)
    Text(
        text = "$first-$second " + stringResource(id = R.string.pts),
        style = MaterialTheme.typography.caption.copy(fontWeight = FontWeight.Normal),
        color = MaterialTheme.colors.primary
    )
}

@Composable
fun Bonus(modifier: Modifier = Modifier, nameBonus: String, bonusValue: String) {
    Row(modifier = modifier, horizontalArrangement = Arrangement.SpaceBetween) {
        Text(
            text = nameBonus,
            style = MaterialTheme.typography.caption.copy(fontWeight = FontWeight.Normal)
        )
        Row {
            Text(text = bonusValue, style = MaterialTheme.typography.body1)
            Spacer(modifier = Modifier.width(MaterialTheme.spacing.small))
            Image(painter = painterResource(id = R.drawable.ic_g_pts), contentDescription = null)
        }
    }
}

@Composable
fun LevelStatusView(levelStatus: LevelStatus) {
    val backgroundColor = when (levelStatus) {
        LevelStatus.Default -> null
        LevelStatus.Current -> MaterialTheme.colors.primary
        LevelStatus.Completed -> Success500
    }
    Box(
        modifier = Modifier
            .height(20.dp)
            .background(
                color = backgroundColor ?: MaterialTheme.colors.secondary,
                shape = RoundedCornerShape(4.dp)
            )
    ) {
        Text(
            text = levelStatus.name,
            style = MaterialTheme.typography.caption,
            modifier = Modifier
                .align(Alignment.Center)
                .padding(horizontal = MaterialTheme.spacing.smallMedium)
        )
    }
}

@Composable
fun LevelName(levelName: String, dotNextName: Boolean) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(text = levelName, style = MaterialTheme.typography.subtitle1)
        if (dotNextName) {
            Spacer(modifier = Modifier.width(MaterialTheme.spacing.small))
            Box(
                modifier = Modifier
                    .background(
                        color = MaterialTheme.colors.primary,
                        shape = CircleShape
                    )
                    .size(4.dp)
            )
        }
    }
}

@Composable
fun ImageSideCard(number: String, numberBackgroundColor: Color, pictureId: Int) {
    Box {
        Image(
            painter = painterResource(id = pictureId),
            contentDescription = null,
            contentScale = ContentScale.Fit
        )
        IndexOnCard(
            number = number,
            modifier = Modifier
                .padding(
                    top = MaterialTheme.spacing.small,
                    start = MaterialTheme.spacing.small
                )
                .size(20.dp)
                .background(color = numberBackgroundColor, shape = CircleShape)
        )

    }
}

@Composable
fun IndexOnCard(modifier: Modifier = Modifier, number: String) {
    Box(
        modifier = modifier
    ) {
        Text(
            text = number,
            style = MaterialTheme.typography.body2.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.align(Alignment.Center)
        )
    }
}


@Composable
fun Progress(currentLevel: Level, currentScore: Int) {
    Column {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .height(32.dp)
        ) {
            Text(text = currentLevel.name, style = MaterialTheme.typography.body1)
            Text(
                text = ParserDecimal.pars(currentScore) + " / " + ParserDecimal.pars(currentLevel.unlockPtsSecond),
                style = MaterialTheme.typography.body1,
                color = Tertiary500
            )
            Text(text = currentLevel.nextName, style = MaterialTheme.typography.body1)
        }
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraSmall))
        LinearProgressIndicator(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(4.dp))
                .height(8.dp),
            backgroundColor = Muted500,
            progress = ((currentScore - currentLevel.unlockPtsFirst).toFloat()
                    / (currentLevel.unlockPtsSecond - currentLevel.unlockPtsFirst)),
            color = Tertiary500
        )
    }
}