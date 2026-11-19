package com.example.navigationlab.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.navigationlab.ui.favorites.FavoritesUiState

@Composable
fun FavoritesScreen(
    uiState: FavoritesUiState,
    onToggleSort: () -> Unit,
    onStudentClick: (Int) -> Unit,
    onBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Mahasiswa Favorit", style = MaterialTheme.typography.headlineMedium)

        OutlinedButton(
            onClick = onToggleSort,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                if (uiState.sortAscending) "Urutan: A-Z (ketuk untuk Z-A)"
                else "Urutan: Z-A (ketuk untuk A-Z)"
            )
        }

        when {
            uiState.isLoading -> {
                Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
            uiState.items.isEmpty() -> {
                Text("Belum ada mahasiswa favorit.")
            }
            else -> {
                LazyColumn(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(uiState.items, key = { it.id }) { item ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { onStudentClick(item.id) }
                        ) {
                            Column(Modifier.padding(16.dp)) {
                                Text(item.name, style = MaterialTheme.typography.titleMedium)
                                Text(item.email)
                            }
                        }
                    }
                }
            }
        }

        Button(onClick = onBack) {
            Text("Kembali")
        }
    }
}