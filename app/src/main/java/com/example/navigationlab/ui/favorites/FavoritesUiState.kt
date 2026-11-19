package com.example.navigationlab.ui.favorites

data class FavoriteItem(
    val id: Int,
    val name: String,
    val email: String
)

data class FavoritesUiState(
    val items: List<FavoriteItem> = emptyList(),
    val sortAscending: Boolean = true,
    val isLoading: Boolean = true
)