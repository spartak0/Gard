package ru.spartak.gard.data

import android.app.Activity
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.PhoneAuthProvider
import ru.spartak.gard.domain.FirebaseRepository

class FirebaseRepositoryImpl(private val firebaseService: FirebaseService) : FirebaseRepository {
    override fun sendVerificationCode(
        phoneNumber: String,
        callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks,
        activity: Activity
    ) {
        firebaseService.sendVerificationCode(phoneNumber, callbacks, activity)
    }

    override fun resendVerificationCode(
        phoneNumber: String,
        token: PhoneAuthProvider.ForceResendingToken?,
        callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks
    ) {
        firebaseService.resendVerificationCode(phoneNumber, token, callbacks)
    }

    override fun verifyPhoneNumberWithCode(
        verificationId: String?,
        code: String
    ): Task<AuthResult> {
        return firebaseService.verifyPhoneNumberWithCode(verificationId, code)
    }

    override fun updateUser(firebaseUser: FirebaseUser): Task<Void> {
        return firebaseService.updateUser(firebaseUser)
    }


}