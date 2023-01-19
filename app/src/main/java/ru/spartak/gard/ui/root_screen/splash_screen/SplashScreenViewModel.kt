package ru.spartak.gard.ui.root_screen.splash_screen

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.spartak.gard.domain.repository.FirebaseRepository
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel @Inject constructor(val firebaseRepository: FirebaseRepository):ViewModel() {
    fun getCurrentUser():FirebaseUser?{
        return firebaseRepository.getCurrentUser()
    }
}