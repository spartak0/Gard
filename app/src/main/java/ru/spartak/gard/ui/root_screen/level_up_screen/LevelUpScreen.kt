package ru.spartak.gard.ui.root_screen.level_up_screen

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import ru.spartak.gard.R
import ru.spartak.gard.ui.details.BackBtn
import ru.spartak.gard.ui.details.TopBar
import ru.spartak.gard.ui.theme.GardTheme
import ru.spartak.gard.ui.theme.Success500
import ru.spartak.gard.ui.theme.Text50
import ru.spartak.gard.ui.theme.spacing

@Composable
fun LevelUpScreen(navController: NavController) {
    GardTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        listOf(
                            Color(
                                0xFFAB27DB
                            ),
                            Color(0xFF17171A),
                        )
                    ),
                    alpha = 0.9F
                )
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Spacer(modifier = Modifier.height(38.dp+MaterialTheme.spacing.small))
                LevelUpTopBar(
                    modifier = Modifier
                        .padding(horizontal = MaterialTheme.spacing.medium)
                        .fillMaxWidth()
                        .height(41.dp),
                    backOnClick = {
                        navController.navigateUp()
                    }
                )
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.large))
                Image(
                    painter = painterResource(id = R.drawable.man),
                    modifier = Modifier.size(300.dp),
                    contentDescription = null
                )
                Ribbon(
                    modifier = Modifier
                        .absoluteOffset(y = (-47).dp)
                        .background(MaterialTheme.colors.primary)
                        .fillMaxWidth()
                        .height(106.dp),
                    levelName = "Skilled",
                    levelNumber = "3"
                )
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.large))
                BonusItem(
                    modifier = Modifier
                        .padding(horizontal = 60.dp)
                        .fillMaxWidth(),
                    name = "Starting bonus", value = "400"
                )
                Spacer(Modifier.height(MaterialTheme.spacing.smallMedium))
                BonusItem(
                    modifier = Modifier
                        .padding(horizontal = 60.dp)
                        .fillMaxWidth(),
                    name = "Task bonus", value = "+20%"
                )

            }
            GetBonusBtn(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(horizontal = MaterialTheme.spacing.medium)
                    .padding(bottom = 48.dp)
                    .fillMaxWidth()
                    .height(41.dp),
                onClick = {navController.navigateUp()}
            )
        }
    }
}

@Composable
fun GetBonusBtn(modifier: Modifier, onClick: () -> Unit) {
    Surface(
        modifier = modifier
            .clip(RoundedCornerShape(4.dp))
            .clickable {
                onClick()
            },
        color = Success500
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Get your bonus",
                style = MaterialTheme.typography.body2.copy(fontWeight = FontWeight.Normal)
            )
            Spacer(modifier = Modifier.width(MaterialTheme.spacing.extraSmall))
            Icon(
                painter = painterResource(id = R.drawable.ic_arrow_forward),
                contentDescription = null,
                tint = Text50
            )
        }
    }
}

@Composable
fun BonusItem(modifier: Modifier, name: String, value: String) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = name,
            style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.Normal)
        )
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = value, style = MaterialTheme.typography.h6)
            Spacer(modifier = Modifier.width(MaterialTheme.spacing.extraSmall))
            Image(
                painter = painterResource(id = R.drawable.ic_g_pts),
                contentDescription = null
            )
        }
    }
}

@Composable
fun Ribbon(modifier: Modifier, levelName: String, levelNumber: String) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
        Text(text = "Congratulations! You become", style = MaterialTheme.typography.caption)
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraSmall))
        Text(text = levelName, style = MaterialTheme.typography.h5)
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraSmall))
        Text(text = "Level $levelNumber", style = MaterialTheme.typography.caption)
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
    }
}

@Composable
fun LevelUpTopBar(modifier: Modifier, backOnClick: () -> Unit) {
    TopBar(
        subtitleText = "Level Up!",
        modifier = modifier,
        leftView = {
            BackBtn(contentColor = Text50) {
                backOnClick()
            }
        })
}