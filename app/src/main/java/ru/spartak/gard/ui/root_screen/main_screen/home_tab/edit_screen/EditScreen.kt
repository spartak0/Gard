package ru.spartak.gard.ui.root_screen.main_screen.home_tab.edit_screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.spartak.gard.R
import ru.spartak.gard.ui.details.BackBtn
import ru.spartak.gard.ui.details.CustomTextField
import ru.spartak.gard.ui.details.TopBar
import ru.spartak.gard.ui.navigation.RootScreen
import ru.spartak.gard.ui.theme.*

@Composable
fun EditScreen(navController: NavController, rootNavController: NavController) {
    GardTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column(
                Modifier
                    .padding(horizontal = MaterialTheme.spacing.medium)
                    .fillMaxWidth()
            ) {
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))
                EditTopBar(backOnClick = { navController.navigateUp() })
                Spacer(modifier = Modifier.height(36.dp))
                SubtitleNickname()
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraSmall))
                val nickname = remember { mutableStateOf("") }
                val email = remember { mutableStateOf("") }
                NicknameTextField(
                    value = nickname.value,
                    onValueChange = { nickname.value = it },
                    placeholder = "Nagib",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp)
                )
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
                SubtitleEmail()
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraSmall))
                EmailTextField(
                    value = email.value,
                    onValueChange = { email.value = it },
                    placeholder = "123@mail.ru",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp)
                )
            }
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .background(Dark100)
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
            ) {
                val loadState = remember {
                    mutableStateOf(false)
                }
                SaveBtn(
                    text = stringResource(R.string.save),
                    modifier = Modifier
                        .padding(MaterialTheme.spacing.medium)
                        .fillMaxWidth()
                        .height(40.dp),
                    loadState = loadState,
                    onClick = {
                        CoroutineScope(Dispatchers.Main).launch {
                            loadState.value = true
                            //todo remove in vm
                            val tmp = CoroutineScope(Dispatchers.IO).launch {
                                delay(2000)
                            }
                            tmp.join()
                            loadState.value = false
                            rootNavController.navigate(RootScreen.Confirmation.route)
                        }


                    }
                )
                Divider(modifier = Modifier.fillMaxWidth(), thickness = 1.dp, color = Dark200)
            }
        }
    }
}

@Composable
fun SaveBtn(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    text: String,
    loadState: MutableState<Boolean>
) {
    Box(modifier = modifier) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    MaterialTheme.colors.primary,
                    RoundedCornerShape(4.dp)
                )
                .clickable {
                    onClick()
                },
            contentAlignment = Alignment.Center,
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = text,
                    style = MaterialTheme.typography.body2.copy(fontWeight = FontWeight.Medium)
                )
                AnimatedVisibility(visible = loadState.value) {
                    Spacer(modifier = Modifier.width(MaterialTheme.spacing.extraSmall))
                    CircularProgressIndicator(
                        modifier = Modifier
                            .padding(start = MaterialTheme.spacing.extraSmall)
                            .size(20.dp),
                        strokeWidth = 2.dp,
                        color = Text50
                    )
                }
            }
        }
        if (loadState.value) Surface(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(4.dp))
                .alpha(0.5f), color = Color.Black
        ) {
        }
    }
}

@Composable
fun SubtitleEmail() {
    Text(
        text = stringResource(R.string.email),
        style = MaterialTheme.typography.caption.copy(fontWeight = FontWeight.Normal),
        color = Text500
    )
}

@Composable
fun SubtitleNickname() {
    Text(
        text = stringResource(R.string.nickname),
        style = MaterialTheme.typography.caption.copy(fontWeight = FontWeight.Normal),
        color = Text500
    )
}

@Composable
fun EmailTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier
) {
    CustomTextField(
        value = value, onValueChange = onValueChange, placeholder = placeholder, modifier = modifier
    )
}

@Composable
fun NicknameTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier
) {
    CustomTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = placeholder,
        modifier = modifier
    )
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun OutlinedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholderText: String = "",
    modifier: Modifier,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    colors: TextFieldColors = TextFieldDefaults.outlinedTextFieldColors(),
    cursorColor: Brush = SolidColor(White),

    ) {
    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier
            .clip(RoundedCornerShape(4.dp)),
        singleLine = true,
        cursorBrush = cursorColor,
        decorationBox = { innerTextField ->
            TextFieldDefaults.OutlinedTextFieldDecorationBox(
                placeholder = {
                    Text(
                        text = placeholderText,
                        style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.Normal),
                        color = Text600
                    )
                },
                value = value,
                innerTextField = innerTextField,
                enabled = true,
                singleLine = true,
                visualTransformation = VisualTransformation.None,
                interactionSource = interactionSource,
                isError = false,
                colors = colors,
                contentPadding = PaddingValues(horizontal = MaterialTheme.spacing.smallMedium),
                border = {
                    TextFieldDefaults.BorderBox(
                        enabled = true,
                        isError = false,
                        interactionSource = interactionSource,
                        colors = colors,
                        shape = RoundedCornerShape(4.dp),
                        focusedBorderThickness = 1.dp,
                        unfocusedBorderThickness = 1.dp
                    )
                }
            )
        },
        textStyle = MaterialTheme.typography.body1.copy(
            color = Text50,
            fontWeight = FontWeight.Normal
        ),
    )
}

@Composable
fun EditTopBar(backOnClick: () -> Unit) {
    TopBar(
        subtitleText = stringResource(id = R.string.edit),
        modifier = Modifier
            .fillMaxWidth()
            .height(41.dp),
        leftView = { BackBtn { backOnClick() } },
    )
}
