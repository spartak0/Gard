package ru.spartak.gard.ui.root_screen.login_screen

import android.app.Activity
import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.PhoneAuthProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import ru.spartak.gard.domain.model.NetworkResult
import ru.spartak.gard.domain.repository.ApiRepository
import ru.spartak.gard.domain.repository.FirebaseRepository
import java.util.Collections
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModel @Inject constructor(
    private val firebaseRepository: FirebaseRepository,
    private val apiRepository: ApiRepository
) :
    ViewModel() {

    lateinit var resendToken: PhoneAuthProvider.ForceResendingToken
    lateinit var storedVerificationId: String

    fun sendVerificationCode(
        phoneNumber: String,
        callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks,
        activity: Activity
    ) {
        firebaseRepository.sendVerificationCode(phoneNumber, callbacks, activity)
    }

    fun resendVerificationCode(
        phoneNumber: String,
        token: PhoneAuthProvider.ForceResendingToken?,
        callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks,
        activity: Activity
    ) {
        firebaseRepository.resendVerificationCode(phoneNumber, token, callbacks, activity)
    }


    fun verifyPhoneNumberWithCode(
        verificationId: String?,
        code: String,
        onCompleteListener: OnCompleteListener<AuthResult>
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            firebaseRepository.verifyPhoneNumberWithCode(verificationId, code)
                .addOnCompleteListener(onCompleteListener)
        }
    }

    fun updateUser(firebaseUser: FirebaseUser, onCompleteListener: OnCompleteListener<Void>) {
        CoroutineScope(Dispatchers.IO).launch {
            firebaseRepository.updateUser(firebaseUser).addOnCompleteListener(onCompleteListener)
        }
    }

    fun getStatByUsername(username: String) = runBlocking(Dispatchers.IO) {
    }

    fun getStat(accountId: String) = runBlocking(Dispatchers.IO) {
        apiRepository.getStats(accountId)
            .catch { e -> Log.e("AAA", "getStat: ", e) }
            .last()
    }

    fun getId(username: String): NetworkResult<String> = runBlocking(Dispatchers.IO) {
        apiRepository.getAccountId(username)
            .catch { e -> Log.e("AAA", "getId: ", e) }
            .last()
    }
}