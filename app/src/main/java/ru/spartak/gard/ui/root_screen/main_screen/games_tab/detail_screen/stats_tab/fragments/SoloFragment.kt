package ru.spartak.gard.ui.root_screen.main_screen.games_tab.detail_screen.stats_tab.fragments

import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import ru.spartak.gard.ui.theme.Dark100
import ru.spartak.gard.ui.theme.GardTheme
import ru.spartak.gard.ui.theme.Text400
import ru.spartak.gard.ui.theme.spacing
import ru.spartak.gard.utils.ParserDecimal

@Composable
fun SoloFragment() {
    val statList = listOf(
        Pair("Score", "8998210"),
        Pair("Matches", "28888"),
        Pair("Matches2", "3888"),
        Pair("Top 3/5/10", "23"),
        Pair("Top 3/5/102", "23"),
        Pair("Top 3/5/103", "23"),
        Pair("Top 3/5/104", "23"),
        Pair("Time played", "1000D 23H 14M"),
        Pair("Time played", "1000D 23H 14M"),
    )
    GardTheme() {
        StatGrid(statList = statList)
    }
}
//            FlowRow(
//                modifier = Modifier.fillMaxWidth(),
//                crossAxisSpacing = MaterialTheme.spacing.medium,
//                mainAxisSpacing = MaterialTheme.spacing.smallMedium,
//            ) {
//                for (index in (statList.indices)) {
//                    Column(
//                        modifier = Modifier
//                            .height(68.dp)
//                            .width((LocalConfiguration.current.screenWidthDp.dp - 32.dp - 12.dp) / 2)
//                    ) {
//                        Text(
//                            text = statList[index].first,
//                            style = MaterialTheme.typography.caption.copy(fontWeight = FontWeight.Normal),
//                            color = Text400
//                        )
//                        Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraSmall))
//                        Text(
//                            text = ParserDecimal.pars(statList[index].second),
//                            style = if (index != 0) MaterialTheme.typography.body1 else MaterialTheme.typography.h5
//                        )
//                    }
//                    if (index % 2 != 0) Divider(
//                        modifier = Modifier.width(LocalConfiguration.current.screenWidthDp.dp - 32.dp),
//                        color = Dark200,
//                        thickness = 1.dp
//                    )
//                }
//            }
