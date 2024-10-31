package com.example.petsapp.ui.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.petsapp.PetViewModel
import com.example.petsapp.ThemeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PetDetailScreen(navController: NavController, petId: Int?, viewModel: PetViewModel, themeViewModel: ThemeViewModel) {
    val pet = viewModel.allPets.value?.find { it.id == petId }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detalles de ${pet?.name ?: "Mascota"}") },
                actions = {
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
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Atrás")
                    }
                }
            )
        },
        bottomBar = {
            Button(
                onClick = { /* Handle adopt action */ },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFEB722F),
                    contentColor = Color.White
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text("Adoptame")
            }
        }
    ) { padding ->
        pet?.let {
            Column(modifier = Modifier
                .padding(padding)
                .padding(16.dp)) {

                Image(painterResource(id = pet.image), contentDescription = "Imagen de ${pet.id}",
                    modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = it.name, style = MaterialTheme.typography.titleMedium, fontSize = 40.sp)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = it.category, style = MaterialTheme.typography.bodyMedium,fontSize = 18.sp)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "${it.age} años", style = MaterialTheme.typography.bodyMedium,fontSize = 16.sp)
                Text(text = "${it.weight} kg", style = MaterialTheme.typography.bodyMedium,fontSize = 16.sp)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = it.about, style = MaterialTheme.typography.bodySmall, fontSize = 18.sp)
            }
        } ?: Text("Mascota no encontrada", modifier = Modifier.padding(16.dp))
    }
}


