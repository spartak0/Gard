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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
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
import ru.spartak.gard.ui.details.BackBtn
import ru.spartak.gard.ui.details.TopBar
import ru.spartak.gard.ui.details.topAlign
import ru.spartak.gard.ui.navigation.Graphs
import ru.spartak.gard.ui.navigation.navigate
import ru.spartak.gard.ui.root_screen.main_screen.home_tab.edit_screen.OutlinedTextField
import ru.spartak.gard.ui.root_screen.main_screen.home_tab.profile_screen.Toast
import ru.spartak.gard.ui.theme.Error600
import ru.spartak.gard.ui.theme.GardTheme
import ru.spartak.gard.ui.theme.Tertiary500
import ru.spartak.gard.ui.theme.spacing
import ru.spartak.gard.utils.StatusBarHeight
import kotlin.time.Duration.Companion.seconds

private lateinit var resendToken: PhoneAuthProvider.ForceResendingToken
private lateinit var storedVerificationId: String

@Composable
fun ConfirmationScreen(
    navController: NavController,
    bundleNavigation: Bundle,
    viewModel: ConfirmationViewModel = hiltViewModel(),
) {
    val phoneNumber = "+79525500322"
    val text = remember {
        mutableStateOf("")
    }
    val toastState =
        remember { mutableStateOf(Triple(false, ToastState.Success as ToastState, "")) }
    val borderColor = animateColorAsState(
        targetValue = if (toastState.value.first && toastState.value.second == ToastState.Error
        ) Error600 else Tertiary500
    )

    val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        override fun onVerificationCompleted(credential: PhoneAuthCredential) {
            Log.d("AAA", "onVerificationCompleted:$credential")
        }

        override fun onVerificationFailed(e: FirebaseException) {
            Log.w("AAA", "onVerificationFailed", e)

            when (e) {
                is FirebaseAuthInvalidCredentialsException -> {
                    // Invalid request
                }
                is FirebaseTooManyRequestsException -> {
                    // The SMS quota for the project has been exceeded
                }
                else -> toastState.value = Triple(true, ToastState.Error as ToastState, e.message!!)
            }
        }

        override fun onCodeSent(
            verificationId: String,
            token: PhoneAuthProvider.ForceResendingToken
        ) {
            Log.d("AAA", "onCodeSent:$verificationId")
            storedVerificationId = verificationId
            resendToken = token
        }
    }

    viewModel.sendVerificationCode(phoneNumber, callbacks, navController.context as Activity)
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
                        verificationId = storedVerificationId,
                        code = str
                    ) { taskVerify ->
                        if (taskVerify.isSuccessful) {
                            taskVerify.result?.user?.let { user ->
                                viewModel.updateUser(user) { taskUpdateUser ->
                                    if (taskUpdateUser.isSuccessful) navController.navigate(
                                        Graphs.Main,
                                        bundleNavigation
                                    )
                                    else toastState.value =
                                        Triple(true, ToastState.Error, "Failed to update user")
                                }
                            }
                        } else {
                            toastState.value =
                                Triple(true, ToastState.Error, "Wrong code")
                        }
                    }
                }
                //todo подумать как упростить
//                code = storedVerificationId.value,
//                onSuccess = {
//                    navController.navigate(
//                        Graphs.Main,
//                        bundleNavigation
//
//                    )
//                },
//                onError = {
//                    toastState.value = Triple(true, ToastState.Error, "Wrong code")
//                }
            )
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
            SendAgainBtn(
                modifier = Modifier
                    .padding(horizontal = MaterialTheme.spacing.medium)
                    .fillMaxWidth()
                    .height(41.dp),
                onClick = {
                    viewModel.resendVerificationCode(phoneNumber, resendToken, callbacks)
                    toastState.value =
                        Triple(
                            true,
                            ToastState.Info,
                            "Another code was sended to your phone number"
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
            text = "Send  code again ${if (tick.value != 0) formatTime else ""}",
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
        text = "We’ve send a confirmation code to your new E-mail.\nType it to proceed",
        modifier = Modifier.padding(horizontal = MaterialTheme.spacing.medium),
        style = MaterialTheme.typography.body2.copy(fontWeight = FontWeight.Normal)
    )
}

@Composable
fun SubtitleConfirmation() {
    Text(
        text = "Confirmation",
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
