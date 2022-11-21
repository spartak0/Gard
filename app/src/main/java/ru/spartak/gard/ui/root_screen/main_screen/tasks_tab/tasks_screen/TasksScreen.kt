package ru.spartak.gard.ui.root_screen.main_screen.tasks_tab.tasks_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import ru.spartak.gard.R
import ru.spartak.gard.ui.details.Border
import ru.spartak.gard.ui.details.CustomBtn
import ru.spartak.gard.ui.details.TopBar
import ru.spartak.gard.ui.details.border
import ru.spartak.gard.ui.navigation.Screen
import ru.spartak.gard.ui.theme.*

@Composable
fun TasksScreen(navController: NavController) {
    val list = listOf<String>()

    GardTheme {
        Column {
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))
            TasksTopBar()
            if (false) EmptyListTasksContent(
                modifier = Modifier.fillMaxSize(),
                navController = navController
            )
            else TasksContent(modifier = Modifier.fillMaxSize(), navController = navController)
        }
    }
}

@Composable
fun TasksTopBar() {
    TopBar(
        subtitleText = "Tasks",
        modifier = Modifier
            .padding(horizontal = MaterialTheme.spacing.medium)
            .fillMaxWidth()
            .height(41.dp)
    )
}

@Composable
fun TasksContent(modifier: Modifier, navController: NavController) {
    val bottomViewHeight = 73.dp

    Box(modifier = modifier) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = MaterialTheme.spacing.medium)
                .padding(bottom = bottomViewHeight)
                .align(
                    Alignment.Center
                )
        ) {
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.mediumLarge))
            val tmp1 = listOf(TaskStatus.Completed, TaskStatus.Active)
            val tmp2 = listOf(TaskPeriodicity.Daily, TaskPeriodicity.Weekly, TaskPeriodicity.Monthly)
            for (i in tmp1) {
                for (j in tmp2) {
                    TaskCard(
                        modifier = Modifier.fillMaxWidth(),
                        taskStatus = i,
                        taskPeriodicity = j,
                        subtitle = "Monsterkill #30",
                        description = "Make 30 kills in 10 games. Only classical mode is allowed",
                        currentProgress = 23,
                        totalProgress = 30,
                        lastUpdateTime = "15h left",
                        accrual = "+20"
                    )
                    Spacer(modifier = Modifier.height(MaterialTheme.spacing.smallMedium))

                }
            }
        }
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .height(73.dp)
                .background(Dark100)
                .border(bottom = Border(1.dp, Dark200))
        ) {
            CustomBtn(
                text = "Collect",
                color = Success500,
                icon = painterResource(id = R.drawable.ic_arrow_forward),
                modifier = Modifier.padding(MaterialTheme.spacing.medium)
            ) {
                navController.navigate(Screen.CompletedTasks.route)
            }
        }
    }
}

@Composable
fun TaskCard(
    modifier: Modifier,
    taskStatus: TaskStatus,
    taskPeriodicity: TaskPeriodicity,
    subtitle: String,
    description: String,
    currentProgress: Int,
    totalProgress: Int,
    lastUpdateTime: String="",
    accrual: String,
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(4.dp))
            .background(MaterialTheme.colors.secondary)
            .border(start = Border(4.dp, taskPeriodicity.color))
            .padding(MaterialTheme.spacing.medium)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = subtitle, style = MaterialTheme.typography.subtitle1)
            Row(verticalAlignment = Alignment.CenterVertically) {
                if (taskStatus != TaskStatus.Completed) {
                    Text(text = lastUpdateTime, style = MaterialTheme.typography.body2)
                    Spacer(modifier = Modifier.width(MaterialTheme.spacing.small))
                }
                TaskStatusView(taskStatus)
            }
        }
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
        Text(
            text = description,
            style = MaterialTheme.typography.caption.copy(fontWeight = FontWeight.Normal)
        )
        Text(
            text = " ($currentProgress/$totalProgress)",
            style = MaterialTheme.typography.caption.copy(fontWeight = FontWeight.Bold)
        )
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
        val progress = currentProgress.toFloat() / totalProgress
        LinearProgressIndicator(
            progress = progress,
            modifier = Modifier
                .fillMaxWidth()
                .height(4.dp),
            color = when {
                progress == 1F -> Success500
                progress < 0.5 -> Error500
                0.5 <= progress -> Warning500
                else -> Error500
            },
            backgroundColor = Muted700
        )
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.mediumLarge))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = taskPeriodicity.name,
                color = taskPeriodicity.color,
                style = MaterialTheme.typography.subtitle2
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = accrual, style = MaterialTheme.typography.subtitle1)
                Spacer(modifier = Modifier.width(MaterialTheme.spacing.extraSmall))
                Image(
                    painter = painterResource(id = R.drawable.ic_g_pts),
                    modifier = Modifier.size(16.dp),
                    contentDescription = null
                )
            }
        }
    }

}

@Composable
fun TaskStatusView(taskStatus: TaskStatus) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(4.dp))
            .height(20.dp)
            .background(taskStatus.color),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = taskStatus.name,
            style = MaterialTheme.typography.caption.copy(fontWeight = FontWeight.Medium),
            modifier = Modifier.padding(horizontal = MaterialTheme.spacing.smallMedium)
        )

    }
}

fun PeriodicityColor(periodicity: String): Color {
    return when (periodicity.lowercase()) {
        "daily" -> Tertiary50
        "weekly" -> Tertiary200
        "Monthly" -> Tertiary400
        else -> Tertiary50
    }
}

@Composable
fun EmptyListTasksContent(modifier: Modifier, navController: NavController) {
    Column(modifier = modifier) {
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.large))
        Image(
            painter = painterResource(id = R.drawable.disconnected_game),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            contentScale = ContentScale.FillHeight
        )
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.large))
        Text(
            text = "Oops!",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h4.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(38.dp)
        )
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))
        Text(
            text = "Your tasks list is empty. That’s because your haven’t got any games connected yet. Let’s handle this!",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.body2.copy(fontWeight = FontWeight.Normal),
            modifier = Modifier
                .padding(MaterialTheme.spacing.smallMedium)
                .fillMaxWidth()
                .height(38.dp)
        )
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.mediumLarge))
        CustomBtn(
            text = "Connect my first game",
            modifier = Modifier
                .padding(horizontal = MaterialTheme.spacing.medium)
                .fillMaxWidth()
                .height(41.dp),
            icon = painterResource(id = R.drawable.ic_arrow_forward)
        ) {
            navController.navigate(Screen.ConnectGame.route)
        }
    }
}


