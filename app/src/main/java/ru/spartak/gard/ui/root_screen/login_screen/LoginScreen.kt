package ru.spartak.gard.ui.root_screen.login_screen

import android.app.Activity
import android.util.Log
import androidx.compose.animation.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.os.bundleOf
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import ru.spartak.gard.R
import ru.spartak.gard.data.db.firebase.getActivity
import ru.spartak.gard.ui.details.BackBtn
import ru.spartak.gard.ui.details.TopBar
import ru.spartak.gard.ui.details.topAlign
import ru.spartak.gard.ui.root_screen.confirmation_screen.ToastState
import ru.spartak.gard.ui.root_screen.main_screen.games_tab.games_screen.ProceedBtn
import ru.spartak.gard.ui.root_screen.main_screen.home_tab.profile_screen.Toast
import ru.spartak.gard.ui.root_screen.navigation.BottomScreen
import ru.spartak.gard.ui.root_screen.navigation.RootScreen
import ru.spartak.gard.ui.root_screen.navigation.navigate
import ru.spartak.gard.ui.theme.*
import ru.spartak.gard.utils.Constant
import ru.spartak.gard.utils.StatusBarHeight

@Composable
fun LoginScreen(navController: NavController, viewModel: LoginScreenViewModel = hiltViewModel()) {
    val phoneNumber = remember { mutableStateOf("") }
    val toastState =
        remember { mutableStateOf(Triple(false, ToastState.Success as ToastState, "")) }
    val borderColor = animateColorAsState(
        targetValue = if (toastState.value.first && toastState.value.second == ToastState.Error
        ) Error600 else Tertiary500
    )
    val loadStateBtn = remember { mutableStateOf(false) }
    val context = LocalContext.current
    val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        override fun onVerificationCompleted(credential: PhoneAuthCredential) {
            loadStateBtn.value = false
            navController.navigate(
                RootScreen.Confirmation.route, bundleOf(
                    Constant.CONFIRMATION_BUNDLE to bundleOf(
                        Constant.MAIN_GRAPH_START_DESTINATION to BottomScreen.HomeScreen.route,
                        Constant.PHONE_NUMBER to "+7${phoneNumber.value}"
                    )
                )
            )
        }

        override fun onVerificationFailed(e: FirebaseException) {
            loadStateBtn.value=false
            when (e) {
                is FirebaseAuthInvalidCredentialsException -> {
                    toastState.value = Triple(
                        true,
                        ToastState.Error as ToastState,
                        context.getString(R.string.invalid_phone_number)
                    )
                }
                is FirebaseTooManyRequestsException -> {
                    toastState.value = Triple(
                        true,
                        ToastState.Error as ToastState,
                        context.getString(R.string.too_many_requests)
                    )
                }
                else -> {
                    Log.e("AAA", "onVerificationFailed: ${e.message}")
                    toastState.value = Triple(true, ToastState.Error as ToastState, e.message!!)
                }
            }
        }

        override fun onCodeSent(
            verificationId: String,
            token: PhoneAuthProvider.ForceResendingToken
        ) {
            viewModel.storedVerificationId = verificationId
            viewModel.resendToken = token
        }
    }

    GardTheme {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Spacer(modifier = Modifier.height(38.dp + MaterialTheme.spacing.small))
            LoginTopBar(backOnClick = { navController.navigateUp() })
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.large))
            Image(
                painter = painterResource(id = R.drawable.game_equipments),
                contentDescription = null,
                modifier = Modifier.size(274.dp, 118.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.large))
            Text(
                text = stringResource(R.string.type_your_phone_number),
                modifier = Modifier.padding(horizontal = MaterialTheme.spacing.medium),
                style = MaterialTheme.typography.h6.copy(fontSize = 30.sp)
            )
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))
            Text(
                text = stringResource(R.string.use_phone_number_sign_in),
                modifier = Modifier.padding(horizontal = MaterialTheme.spacing.medium),
                style = MaterialTheme.typography.body2.copy(fontWeight = FontWeight.Normal)
            )
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.mediumLarge))
            LoginTextField(
                text = phoneNumber.value,
                onValueChange = { phoneNumber.value = it },
                modifier = Modifier
                    .padding(horizontal = MaterialTheme.spacing.medium)
                    .fillMaxWidth()
                    .height(40.dp),
                borderColor = SolidColor(borderColor.value),
            )
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
            ProceedBtn(
                modifier = Modifier
                    .padding(horizontal = MaterialTheme.spacing.medium)
                    .fillMaxWidth()
                    .height(41.dp),
                loadState = loadStateBtn.value
            ) {
                loadStateBtn.value = true
                viewModel.sendVerificationCode("+7${phoneNumber.value}", callbacks,context.getActivity()!!)
            }
        }
        AnimatedVisibility(
            visible = toastState.value.first,
            enter = fadeIn() + expandVertically(),
            exit = fadeOut() + shrinkVertically()
        ) {
            Toast(
                iconId = toastState.value.second.iconId,
                backgroundColor = toastState.value.second.color,
                text = toastState.value.third,
                modifier = Modifier
                    .padding(
                        start = MaterialTheme.spacing.small,
                        end = MaterialTheme.spacing.small,
                        top = StatusBarHeight.calculate() + 20.dp,
                    )
                    .fillMaxWidth()
                    .height(45.dp)
                    .topAlign(),
                dismiss = {
                    toastState.value = toastState.value.copy(first = false)
                }
            )

        }

    }
}

@Composable
fun LoginTextField(
    text: String,
    onValueChange: (String) -> Unit,
    borderColor: Brush,
    modifier: Modifier
) {
    val mobileNumberTransformer = MobileNumberTransformer()
    MyOutlinedTextField(
        value = text,
        onValueChange = onValueChange,
        visualTransformation = { mobileNumberTransformer.transformNumber(it) },
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
        modifier = modifier
            .border(
                width = 2.dp,
                brush = borderColor,
                shape = RoundedCornerShape(4.dp)
            )
            .clip(RoundedCornerShape(4.dp))
            .background(brush = SolidColor(Color(0xFF059669).copy(0.1F))),
        cursorColor = SolidColor(Tertiary500)
    )
}

@Composable
fun LoginTopBar(backOnClick: () -> Unit) {
    TopBar(
        subtitleText = "",
        modifier = Modifier
            .padding(horizontal = MaterialTheme.spacing.medium)
            .fillMaxWidth()
            .height(41.dp),
        leftView = { BackBtn { backOnClick() } },
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MyOutlinedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholderText: String = "",
    modifier: Modifier,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
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
        keyboardOptions = keyboardOptions,
        cursorBrush = cursorColor,
        visualTransformation = visualTransformation,
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