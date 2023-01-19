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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import ru.spartak.gard.data.network.ApiRepositoryImpl
import ru.spartak.gard.domain.model.NetworkResult
import ru.spartak.gard.domain.repository.ApiRepository
import ru.spartak.gard.domain.repository.FirebaseRepository
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModel @Inject constructor(
    private val firebaseRepository: FirebaseRepository,
    private val apiRepository: ApiRepository
) :
    ViewModel() {

    lateinit var resendToken: PhoneAuthProvider.ForceResendingToken
    lateinit var storedVerificationId: String

//    private val _phoneNumber = MutableStateFlow("")
//    val phoneNumber = _phoneNumber.asStateFlow()

    fun sendVerificationCode(
        phoneNumber: String,
        callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks,
        activity: Activity
    ) {
        firebaseRepository.sendVerificationCode(phoneNumber, callbacks,activity)
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

    fun stat() {
        CoroutineScope(Dispatchers.IO).launch {
            apiRepository.getStats("Mikson 75").collect { state ->
                when(state){
                    is NetworkResult.Success -> Log.e("AAA", "stat_success: ${state.data}")
                    is NetworkResult.Error->Log.e("AAA", "stat_error: ${state.message}")
                    is NetworkResult.Loading->Log.e("AAA", "stat_loading")
                    else ->{}
                }
            }
        }
    }
}