package ru.spartak.gard.ui.root_screen.main_screen.tasks_tab.completed_tasks_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ru.spartak.gard.R
import ru.spartak.gard.ui.details.BackBtn
import ru.spartak.gard.ui.details.Border
import ru.spartak.gard.ui.details.TopBar
import ru.spartak.gard.ui.details.border
import ru.spartak.gard.ui.root_screen.main_screen.tasks_tab.tasks_screen.TaskPeriodicity
import ru.spartak.gard.ui.root_screen.main_screen.tasks_tab.tasks_screen.TaskStatus
import ru.spartak.gard.ui.root_screen.main_screen.tasks_tab.tasks_screen.TaskStatusView
import ru.spartak.gard.ui.theme.GardTheme
import ru.spartak.gard.ui.theme.Text500
import ru.spartak.gard.ui.theme.spacing

@Composable
fun CompletedTasksScreen(navController: NavController) {
    GardTheme {
        Column(modifier = Modifier.padding(horizontal = MaterialTheme.spacing.medium)) {
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))
            CompletedTasksTopBar {
                navController.navigateUp()
            }
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.mediumLarge))
            Column(modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())) {
                Text(
                    text = "Today",
                    style = MaterialTheme.typography.body2.copy(fontWeight = FontWeight.Normal),
                    color = Text500
                )
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
                CompletedTaskCard(
                    modifier = Modifier.fillMaxWidth(),
                    subtitle = "Monsterkill #30",
                    taskPeriodicity = TaskPeriodicity.Daily,
                    accrual = "+6"
                )
            }
        }
    }
}

@Composable
fun CompletedTaskCard(
    modifier: Modifier,
    subtitle: String,
    taskPeriodicity: TaskPeriodicity,
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
            Text(
                text = taskPeriodicity.name,
                color = taskPeriodicity.color,
                style = MaterialTheme.typography.subtitle2
            )
            TaskStatusView(TaskStatus.Completed)
        }
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.smallMedium))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = subtitle,
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
fun CompletedTasksTopBar(backOnClick: () -> Unit) {
    TopBar(
        subtitleText = "Completed",
        leftView = {
            BackBtn { backOnClick() }
        },
        modifier = Modifier
            .padding(horizontal = MaterialTheme.spacing.medium)
            .fillMaxWidth()
            .height(41.dp)
    )
}