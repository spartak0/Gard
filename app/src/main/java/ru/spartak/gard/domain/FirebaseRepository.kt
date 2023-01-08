package ru.spartak.gard.domain

import android.app.Activity
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.PhoneAuthProvider

interface FirebaseRepository {
    fun sendVerificationCode(
        phoneNumber: String,
        callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks,
        activity: Activity
    )

    fun resendVerificationCode(
        phoneNumber: String,
        token: PhoneAuthProvider.ForceResendingToken?,
        callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks
    )

    fun verifyPhoneNumberWithCode(verificationId: String?, code: String): Task<AuthResult>
    fun updateUser(firebaseUser: FirebaseUser):Task<Void>
}