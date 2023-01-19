package ru.spartak.gard.data.db.firebase

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.*
import com.google.firebase.auth.PhoneAuthProvider.OnVerificationStateChangedCallbacks
import ru.spartak.gard.ui.main_activity.MainActivity
import java.util.concurrent.TimeUnit

class FirebaseService(
    private val context: Context,
    private val auth: FirebaseAuth,
) {

    fun sendVerificationCode(
        phoneNumber: String,
        callbacks: OnVerificationStateChangedCallbacks,
        activity: Activity
    ) {
        Log.e("AAA", "sendVerificationCode: $phoneNumber")
        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(phoneNumber)       // Phone number to verify
            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
            .setActivity(activity)                 // Activity (for callback binding)
            .setCallbacks(callbacks)          // OnVerificationStateChangedCallbacks
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    fun verifyPhoneNumberWithCode(
        verificationId: String?,
        code: String
    ): Task<AuthResult> {
        val credential = PhoneAuthProvider.getCredential(verificationId!!, code)
        return signInWithPhoneAuthCredential(credential)
    }

    fun resendVerificationCode(
        phoneNumber: String,
        token: PhoneAuthProvider.ForceResendingToken?,
        callbacks: OnVerificationStateChangedCallbacks
    ) {
        val optionsBuilder = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(phoneNumber)       // Phone number to verify
            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
            .setActivity(context as Activity)                 // Activity (for callback binding)
            .setCallbacks(callbacks)          // OnVerificationStateChangedCallbacks
        if (token != null) {
            optionsBuilder.setForceResendingToken(token) // callback's ForceResendingToken
        }
        PhoneAuthProvider.verifyPhoneNumber(optionsBuilder.build())
    }

    private fun signInWithPhoneAuthCredential(
        credential: PhoneAuthCredential
    ): Task<AuthResult> {
        return auth.signInWithCredential(credential)
    }

    fun updateUser(firebaseUser: FirebaseUser): Task<Void> {
        return auth.updateCurrentUser(firebaseUser)
    }

    fun getCurrentUser(): FirebaseUser? {
        return auth.currentUser
    }

    fun signOut(){
        auth.signOut()
    }
    fun getPhoneNumber(){
    }
}

fun Context.getActivity(): Activity? {
    var currentContext = this
    while (currentContext is ContextWrapper) {
        if (currentContext is AppCompatActivity) {
            return currentContext
        }
        if (currentContext is Activity) {
            return currentContext
        }
        currentContext = currentContext.baseContext
    }
    return null
}