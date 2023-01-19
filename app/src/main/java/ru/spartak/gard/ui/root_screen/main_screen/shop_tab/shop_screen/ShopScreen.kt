package ru.spartak.gard.ui.root_screen.main_screen.shop_tab.shop_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ru.spartak.gard.R
import ru.spartak.gard.ui.details.*
import ru.spartak.gard.ui.theme.*
import ru.spartak.gard.utils.ParserDecimal

@SuppressLint("UnrememberedMutableState")
@Composable
fun ShopScreen(navController: NavController) {
    val shopList = listOf(
        Pair(ShopItem.VBucks1000, remember { mutableStateOf(0) }),
        Pair(ShopItem.VBucks2800, remember { mutableStateOf(0) }),
        Pair(ShopItem.VBucks13500, remember { mutableStateOf(0) }),
    )
    val bottomViewHeight = 73.dp

    GardTheme {
        Box {
            Column(
                modifier = Modifier
                    .padding(bottom = bottomViewHeight)
                    .fillMaxSize()
                    .align(Alignment.Center)
            ) {
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))
                ShopTopBar { navController.navigateUp() }
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.mediumLarge))
                Text(
                    text = stringResource(R.string.y_balance),
                    style = MaterialTheme.typography.body2,
                    color = Text500,
                    modifier = Modifier.padding(horizontal = MaterialTheme.spacing.medium)
                )
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.smallMedium))
                BalanceCard(
                    balance = "23",
                    modifier = Modifier
                        .padding(horizontal = MaterialTheme.spacing.medium)
                        .fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
                Text(
                    text = stringResource(R.string.items),
                    style = MaterialTheme.typography.body2,
                    color = Text500,
                    modifier = Modifier.padding(horizontal = MaterialTheme.spacing.medium)
                )
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraSmall))
                ShopGrid(shopList)

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
                    text = stringResource(R.string.checkout),
                    color = Success500,
                    icon = painterResource(id = R.drawable.ic_arrow_forward),
                    modifier = Modifier.padding(MaterialTheme.spacing.medium)
                ) {

                }
            }
        }
    }
}

@Composable
fun ShopGrid(list: List<Pair<ShopItem, MutableState<Int>>>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(horizontal = MaterialTheme.spacing.smallMedium)
    ) {
        items(list) { item ->
            ShopItemCard(
                item.first,
                count = item.second,
                modifier = Modifier
                    .padding(MaterialTheme.spacing.extraSmall)
                    .fillMaxWidth()
            )
        }
    }
}

@Composable
fun ShopItemCard(item: ShopItem, count: MutableState<Int>, modifier: Modifier) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(4.dp))
            .background(MaterialTheme.colors.secondary)
    ) {
        Image(
            painter = painterResource(id = item.imageId),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp)
        )
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))
        Text(
            text = item.name,
            style = MaterialTheme.typography.body1,
            modifier = Modifier.padding(horizontal = MaterialTheme.spacing.medium)
        )
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))
        Text(
            text = ParserDecimal.pars(item.price),
            style = MaterialTheme.typography.body1,
            color = Tertiary500,
            modifier = Modifier.padding(horizontal = MaterialTheme.spacing.medium)
        )
        Spacer(modifier = Modifier.height(20.dp))
        if (count.value == 0) BuyBtn(
            modifier = Modifier
                .padding(horizontal = MaterialTheme.spacing.medium)
                .fillMaxWidth()
                .height(34.dp)
        ) {
            count.value++
        }
        else ShopCountView(
            modifier = Modifier
                .padding(horizontal = MaterialTheme.spacing.medium)
                .fillMaxWidth()
                .height(34.dp),
            count = count
        )
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.smallMedium))
    }

}

@Composable
fun ShopCountView(modifier: Modifier, count: MutableState<Int>) {
    if (count.value > 0) {
        Row(
            modifier = modifier,
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            CounterBtn(iconId = R.drawable.ic_minus, modifier = Modifier.size(34.dp)) {
                count.value--
            }
            Text(
                text = ParserDecimal.pars(count.value),
                style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.Bold),
            )
            CounterBtn(iconId = R.drawable.ic_add, modifier = Modifier.size(34.dp)) {
                count.value++
            }
        }
    }
}

@Composable
fun CounterBtn(modifier: Modifier, iconId: Int, onClick: () -> Unit) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(4.dp))
            .background(MaterialTheme.colors.secondary)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(id = iconId),
            contentDescription = null,
            tint = Text50,
            modifier = Modifier.size(16.dp)
        )
    }
}

@Composable
fun BuyBtn(modifier: Modifier, onClick: () -> Unit) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(4.dp))
            .background(MaterialTheme.colors.primary)
            .clickable { onClick() }, contentAlignment = Alignment.Center
    ) {
        Row(horizontalArrangement = Arrangement.Center) {
            Icon(
                painter = painterResource(id = R.drawable.ic_add),
                modifier = Modifier.size(16.dp),
                contentDescription = null,
                tint = Text50
            )
            Spacer(modifier = Modifier.width(MaterialTheme.spacing.extraSmall))
            Text(
                text = "Buy",
                style = MaterialTheme.typography.caption.copy(fontWeight = FontWeight.Medium)
            )
        }
    }

}

@Composable
fun BalanceCard(modifier: Modifier, balance: String) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(4.dp))
            .background(MaterialTheme.colors.secondary), horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = balance,
            style = MaterialTheme.typography.h6,
            modifier = Modifier.padding(
                start = MaterialTheme.spacing.medium,
                end = MaterialTheme.spacing.medium,
                top = MaterialTheme.spacing.medium,
            ),

            )
        Text(
            text = "Total",
            style = MaterialTheme.typography.body2,
            color = Text500,
            modifier = Modifier.padding(
                start = MaterialTheme.spacing.medium,
                bottom = MaterialTheme.spacing.medium,
            )
        )

    }
}

@Composable
fun ShopTopBar(backOnClick: () -> Unit) {
    TopBar(
        subtitleText = stringResource(R.string.shop),
        modifier = Modifier
            .fillMaxWidth()
            .height(41.dp),
        leftView = { BackBtn { backOnClick() } },
    )
}