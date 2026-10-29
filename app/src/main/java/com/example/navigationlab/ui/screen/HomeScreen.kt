package com.example.navigationlab.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.navigationlab.ui.home.HomeUiState

@Composable
fun HomeScreen(
    uiState: HomeUiState,
    onSelectStudent: (Int) -> Unit,
    onOpenDetail: (Int) -> Unit,
    onOpenProfile: () -> Unit,
    onOpenAbout: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Home", style = MaterialTheme.typography.headlineMedium)

        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(uiState.students) { student ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onSelectStudent(student.id) }
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = student.name,
                            style = MaterialTheme.typography.titleMedium
                        )
                        Text(
                            text = "ID: ${student.id} | ${student.program}",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }
        }

        HorizontalDivider()

        Button(onClick = { onOpenDetail(2026) }) {
            Text("Buka Detail Mahasiswa 2026")
        }
        OutlinedButton(onClick = onOpenProfile) {
            Text("Buka Profile")
        }
        OutlinedButton(onClick = onOpenAbout) {
            Text("About")
        }
    }
}