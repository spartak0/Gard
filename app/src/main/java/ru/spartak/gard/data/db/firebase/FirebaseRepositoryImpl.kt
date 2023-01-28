package ru.spartak.gard.data.db.firebase

import android.app.Activity
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.PhoneAuthProvider
import ru.spartak.gard.domain.repository.FirebaseRepository

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
        callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks,
        activity: Activity
    ) {
        firebaseService.resendVerificationCode(phoneNumber, token, callbacks,activity)
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

    override fun getCurrentUser(): FirebaseUser? {
        return firebaseService.getCurrentUser()
    }

    override fun signOut() {
        firebaseService.signOut()
    }


}