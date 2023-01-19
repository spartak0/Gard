package ru.spartak.gard.ui.root_screen.confirmation_screen

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.text.format.DateUtils
import android.util.Log
import androidx.compose.animation.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.coroutines.delay
import ru.spartak.gard.R
import ru.spartak.gard.data.db.firebase.getActivity
import ru.spartak.gard.ui.details.BackBtn
import ru.spartak.gard.ui.details.TopBar
import ru.spartak.gard.ui.details.topAlign
import ru.spartak.gard.ui.root_screen.login_screen.LoginScreenViewModel
import ru.spartak.gard.ui.root_screen.navigation.Graphs
import ru.spartak.gard.ui.root_screen.navigation.navigate
import ru.spartak.gard.ui.root_screen.main_screen.home_tab.edit_screen.OutlinedTextField
import ru.spartak.gard.ui.root_screen.main_screen.home_tab.profile_screen.Toast
import ru.spartak.gard.ui.theme.Error600
import ru.spartak.gard.ui.theme.GardTheme
import ru.spartak.gard.ui.theme.Tertiary500
import ru.spartak.gard.ui.theme.spacing
import ru.spartak.gard.utils.Constant
import ru.spartak.gard.utils.StatusBarHeight
import kotlin.time.Duration.Companion.seconds

@Composable
fun ConfirmationScreen(
    navController: NavController,
    bundleNavigation: Bundle,
    viewModel: LoginScreenViewModel = hiltViewModel(),
) {
    val phoneNumber = bundleNavigation.getString(Constant.PHONE_NUMBER)?:""
    val text = remember {
        mutableStateOf("")
    }
    val toastState =
        remember { mutableStateOf(Triple(false, ToastState.Success as ToastState, "")) }
    val borderColor = animateColorAsState(
        targetValue = if (toastState.value.first && toastState.value.second == ToastState.Error
        ) Error600 else Tertiary500
    )
    val context = LocalContext.current

    val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        override fun onVerificationCompleted(credential: PhoneAuthCredential) {

            Log.e("AAA", "onVerificationCompleted: 2")
        }

        override fun onVerificationFailed(e: FirebaseException) = when (e) {
            is FirebaseAuthInvalidCredentialsException -> {
                Log.e("AAA", "onVerificationFailed: invalid phone number")
                toastState.value = Triple(
                    true,
                    ToastState.Error as ToastState,
                    context.getString(R.string.invalid_phone_number)
                )
            }
            is FirebaseTooManyRequestsException -> {
                Log.e("AAA", "onVerificationFailed: too many requests")
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

        override fun onCodeSent(
            verificationId: String,
            token: PhoneAuthProvider.ForceResendingToken
        ) {
            Log.e("AAA", "onVerificationSend: 1")
            viewModel.storedVerificationId = verificationId
            viewModel.resendToken = token
        }
    }

    GardTheme {
        Column {
            Spacer(modifier = Modifier.height(38.dp + MaterialTheme.spacing.small))
            ConfirmationTopBar(backOnClick = { navController.navigateUp() })
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.large))
            Image(
                painter = painterResource(id = R.drawable.confirm),
                contentDescription = null,
                modifier = Modifier.padding(horizontal = MaterialTheme.spacing.small)
            )
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.large))
            SubtitleConfirmation()
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))
            TextConfirmation()
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.mediumLarge))
            CodeTextField(
                value = text.value,
                modifier = Modifier
                    .padding(horizontal = MaterialTheme.spacing.medium)
                    .fillMaxWidth()
                    .height(40.dp),
                borderColor = SolidColor(borderColor.value),
                onValueChange = { str ->
                    if (str.length <= 6) text.value = str
                    if (str.length == 6) viewModel.verifyPhoneNumberWithCode(
                        verificationId = viewModel.storedVerificationId,
                        code = str
                    ) { taskVerify ->
                        if (taskVerify.isSuccessful) {
                            taskVerify.result?.user?.let { user ->
                                viewModel.updateUser(user) { taskUpdateUser ->
                                    if (taskUpdateUser.isSuccessful) navController.navigate(
                                        Graphs.Main,
                                        bundleNavigation
                                    )
                                    else {
                                        toastState.value =
                                            Triple(
                                                true,
                                                ToastState.Error,
                                                context.getString(R.string.failed_update_user)
                                            )
                                    }
                                }
                            }
                        } else {
                            toastState.value =
                                Triple(
                                    true,
                                    ToastState.Error,
                                    context.getString(R.string.wrong_code)
                                )
                        }
                    }
                }

            )
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
            SendAgainBtn(
                modifier = Modifier
                    .padding(horizontal = MaterialTheme.spacing.medium)
                    .fillMaxWidth()
                    .height(41.dp),
                onClick = {
                    viewModel.resendVerificationCode(
                        phoneNumber,
                        viewModel.resendToken,
                        callbacks
                    )
                    toastState.value =
                        Triple(
                            true,
                            ToastState.Info,
                            context.getString(R.string.another_code_sended)
                        )
                }
            )
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

@SuppressLint("SimpleDateFormat")
@Composable
fun SendAgainBtn(modifier: Modifier, onClick: () -> Unit) {
    val tick = remember {
        mutableStateOf(10)
    }
    LaunchedEffect(Unit) {
        while (tick.value > 0) {
            delay(1.seconds)
            tick.value--
        }
    }
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(4.dp))
            .clickable(enabled = (tick.value == 0), onClick = { onClick() }),
        contentAlignment = Alignment.Center
    ) {
        val formatTime = "(${DateUtils.formatElapsedTime(tick.value.toLong())})"
        Text(
            text = stringResource(id = R.string.send_code_again) + " ${if (tick.value != 0) formatTime else ""}",
            style = MaterialTheme.typography.body2,
            color = if (tick.value == 0) Tertiary500 else Tertiary500.copy(alpha = 0.5F)
        )
    }
}

@Composable
fun CodeTextField(
    value: String,
    borderColor: Brush,
    onValueChange: (String) -> Unit,
    modifier: Modifier
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
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
fun TextConfirmation() {
    Text(
        text = stringResource(id = R.string.sended_confirmation_code),
        modifier = Modifier.padding(horizontal = MaterialTheme.spacing.medium),
        style = MaterialTheme.typography.body2.copy(fontWeight = FontWeight.Normal)
    )
}

@Composable
fun SubtitleConfirmation() {
    Text(
        text = stringResource(id = R.string.confirmation),
        modifier = Modifier.padding(horizontal = MaterialTheme.spacing.medium),
        style = MaterialTheme.typography.h6.copy(fontSize = 30.sp)
    )
}

@Composable
fun ConfirmationTopBar(backOnClick: () -> Unit) {
    TopBar(
        subtitleText = "",
        modifier = Modifier
            .padding(horizontal = MaterialTheme.spacing.medium)
            .fillMaxWidth()
            .height(41.dp),
        leftView = { BackBtn { backOnClick() } },
    )
}
