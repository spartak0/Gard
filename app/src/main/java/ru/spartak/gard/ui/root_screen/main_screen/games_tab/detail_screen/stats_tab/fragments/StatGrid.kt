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
import ru.spartak.gard.ui.theme.Text400
import ru.spartak.gard.ui.theme.spacing
import ru.spartak.gard.utils.ParserDecimal

@Composable
fun StatGrid(statList: List<Pair<String,String>>){
    Column {
        val rowAmount = kotlin.math.ceil(statList.size.toDouble() / 2).toInt()
        for (i in (0 until rowAmount)) {
            Column() {
                Row(Modifier.fillMaxWidth()) {
                    for (t in (0..1)) {
                        val index = i * 2 + t
                        val height = if (i == 0) 68.dp else 58.dp
                        val width =
                            (LocalConfiguration.current.screenWidthDp.dp - 32.dp - 12.dp) / 2
                        if (index < statList.size) {
                            Column(
                                modifier = Modifier
                                    .height(height)
                                    .width(width)
                            ) {
                                Text(
                                    text = statList[index].first,
                                    style = MaterialTheme.typography.caption.copy(fontWeight = FontWeight.Normal),
                                    color = Text400
                                )
                                Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraSmall))
                                Text(
                                    text = ParserDecimal.pars(statList[index].second),
                                    style = if (index != 0) MaterialTheme.typography.body1 else MaterialTheme.typography.h5
                                )
                            }
                            if (t == 0) Spacer(modifier = Modifier.width(MaterialTheme.spacing.smallMedium))
                        }
                    }
                }
            }
            if (i != rowAmount - 1) {
                Divider(modifier = Modifier.fillMaxWidth(), color = Dark100, thickness = 1.dp)
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
            }
        }
    }
}
