// PetDetailScreen.kt
package com.example.petsapp.ui.details

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.petsapp.Pet
import com.example.petsapp.PetViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PetDetailScreen(navController: NavController, petId: Int?, viewModel: PetViewModel) {
    val pet = viewModel.allPets.value?.find { it.id == petId }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detalles de ${pet?.name ?: "Mascota"}") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Atrás")
                    }
                }
            )
        }
    ) { padding ->
        pet?.let {
            Column(modifier = Modifier.padding(padding).padding(16.dp)) {
                Text(text = "Nombre: ${it.name}", style = MaterialTheme.typography.titleMedium)
                Text(text = "Raza: ${it.category}", style = MaterialTheme.typography.bodyMedium)
                Text(text = "Edad: ${it.age} años", style = MaterialTheme.typography.bodyMedium)
                Text(text = "Peso: ${it.weight} kg", style = MaterialTheme.typography.bodyMedium)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Descripción:", style = MaterialTheme.typography.bodyMedium)
                Text(text = it.about, style = MaterialTheme.typography.bodySmall)
            }
        } ?: Text("Mascota no encontrada", modifier = Modifier.padding(16.dp))
    }
}
