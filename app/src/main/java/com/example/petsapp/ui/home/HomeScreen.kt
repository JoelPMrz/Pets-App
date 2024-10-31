package com.example.petsapp.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.petsapp.Pet
import com.example.petsapp.PetViewModel
import com.example.petsapp.PreferencesManager
import com.example.petsapp.ThemeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController, viewModel: PetViewModel, themeViewModel: ThemeViewModel) {
    val petList by viewModel.allPets.observeAsState(emptyList())

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Lista de Mascotas") }, actions = {
                Switch(
                    checked = themeViewModel.isDarkMode,
                    onCheckedChange = { themeViewModel.toggleDarkMode() },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = Color(0xFFEB722F),
                        uncheckedThumbColor = Color.LightGray,
                        checkedTrackColor = Color(0xFFEB722F).copy(alpha = 0.5f),
                        uncheckedTrackColor = Color.LightGray.copy(alpha = 0.5f)
                    )
                )
            })
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
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.Top
        ) {
            Image(
                painter = painterResource(id = pet.image),
                contentDescription = "Imagen de ${pet.name}",
                modifier = Modifier
                    .padding(6.dp)
                    .width(160.dp)
                    .height(120.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(top = 6.dp)
            ) {
                Text(
                    text = pet.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontSize = 26.sp,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = pet.category,
                    style = MaterialTheme.typography.bodyMedium,
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    text = "${pet.age} años",
                    style = MaterialTheme.typography.bodySmall,
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}

