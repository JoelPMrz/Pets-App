package com.example.petsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.petsapp.ui.details.PetDetailScreen
import com.example.petsapp.ui.home.HomeScreen
import com.example.petsapp.ui.theme.PetsAppTheme


class MainActivity : ComponentActivity() {
    private lateinit var preferencesManager: PreferencesManager
    private val themeViewModel: ThemeViewModel by viewModels { ThemeViewModelFactory(preferencesManager) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        preferencesManager = PreferencesManager(applicationContext)

        val database = Room.databaseBuilder(
            applicationContext,
            PetDatabase::class.java, "pet_database"
        ).build()

        val repository = PetRepository(database.petDao())
        val viewModel: PetViewModel by viewModels { PetViewModelFactory(repository) }

        setContent {
            PetsAppTheme(darkTheme = themeViewModel.isDarkMode) {
                val navController = rememberNavController()
                NavHost(navController, startDestination = "home") {
                    composable("home") {
                        HomeScreen(navController, viewModel, themeViewModel)
                    }
                    composable("details/{petId}") { backStackEntry ->
                        val petId = backStackEntry.arguments?.getString("petId")
                        PetDetailScreen(navController, petId?.toIntOrNull(), viewModel, themeViewModel)
                    }
                }
            }
        }
    }
}


