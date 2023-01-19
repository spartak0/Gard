package ru.spartak.gard.ui.root_screen.main_screen.home_tab.profile_screen

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.spartak.gard.domain.repository.FirebaseRepository
import javax.inject.Inject

@HiltViewModel
class ProfileScreenViewModel @Inject constructor(private val firebaseRepository: FirebaseRepository):ViewModel() {
    fun signOut(){
        firebaseRepository.signOut()
    }
}