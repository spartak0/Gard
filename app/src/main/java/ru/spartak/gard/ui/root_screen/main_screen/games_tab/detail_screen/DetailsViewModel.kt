package ru.spartak.gard.ui.root_screen.main_screen.games_tab.detail_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.viewpager.widget.ViewPager
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import dagger.hilt.InstallIn
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ActivityRetainedScoped
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor() : ViewModel() {
    val seasonList = listOf(
        "Chapter 3: Season 3",
        "Chapter 3: Season 2",
        "Chapter 3: Season 1",
        "Chapter 2: Season 3",
        "Chapter 2: Season 2",
        "Chapter 2: Season 1",
    )
    private val _selectedSeason = MutableStateFlow(seasonList.first())
    val selectedSeason = _selectedSeason.asStateFlow()

    fun changeSelectedSeason(season: String) {
        if (seasonList.contains(season)) {
            viewModelScope.launch(Dispatchers.IO) {
                delay(100)
                _selectedSeason.value = season
            }
        }
    }
}