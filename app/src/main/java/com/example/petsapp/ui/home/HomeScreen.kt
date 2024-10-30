package com.example.petsapp.ui.home


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.petsapp.Pet
import com.example.petsapp.PetViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController, viewModel: PetViewModel) {
    val petList by viewModel.allPets.observeAsState(emptyList())

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Lista de Mascotas") })
        }
    ) { padding ->
        LazyColumn(contentPadding = padding, modifier = Modifier.fillMaxSize()) {
            items(petList) { pet ->
                PetItem(pet) {
                    navController.navigate("details/${pet.id}")
                }
            }
        }
    }
}

@Composable
fun PetItem(pet: Pet, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() },
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = pet.name, style = MaterialTheme.typography.titleMedium)
            Text(text = pet.category, style = MaterialTheme.typography.bodyMedium)
            Text(text = "Edad: ${pet.age} años", style = MaterialTheme.typography.bodySmall)
        }
    }
}
