package ru.spartak.gard.ui.root_screen.confirmation_screen

import android.app.Activity
import androidx.lifecycle.ViewModel
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.PhoneAuthProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.spartak.gard.domain.FirebaseRepository
import javax.inject.Inject

@HiltViewModel
class ConfirmationViewModel @Inject constructor(private val firebaseRepository: FirebaseRepository) :
    ViewModel() {
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
        callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks
    ) {
        firebaseRepository.resendVerificationCode(phoneNumber, token, callbacks)
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
}