package com.example.navigationlab.ui.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.navigationlab.data.preferences.UserPreferencesRepository
import com.example.navigationlab.data.repository.FavoriteRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class FavoritesViewModel(
    favoriteRepository: FavoriteRepository,
    private val preferencesRepository: UserPreferencesRepository
) : ViewModel() {

    val uiState: StateFlow<FavoritesUiState> = combine(
        favoriteRepository.observeFavorites(),
        preferencesRepository.sortAscending
    ) { favorites, ascending ->
        val items = favorites.map {
            FavoriteItem(
                id = it.id,
                name = "${it.firstName} ${it.lastName}".trim(),
                email = it.email
            )
        }
        FavoritesUiState(
            items = if (ascending) {
                items.sortedBy { it.name.lowercase() }
            } else {
                items.sortedByDescending { it.name.lowercase() }
            },
            sortAscending = ascending,
            isLoading = false
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = FavoritesUiState()
    )

    fun toggleSortOrder() {
        viewModelScope.launch {
            preferencesRepository.saveSortAscending(!uiState.value.sortAscending)
        }
    }

    companion object {
        fun factory(
            favoriteRepository: FavoriteRepository,
            preferencesRepository: UserPreferencesRepository
        ): ViewModelProvider.Factory =
            object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return FavoritesViewModel(favoriteRepository, preferencesRepository) as T
                }
            }
    }
}