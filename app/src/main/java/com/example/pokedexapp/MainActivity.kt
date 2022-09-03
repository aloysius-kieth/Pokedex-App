package com.example.pokedexapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.toLowerCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.pokedexapp.Constants.DOMINANT_COLOR
import com.example.pokedexapp.Constants.POKEMON_DETAIL_SCREEN
import com.example.pokedexapp.Constants.POKEMON_LIST_SCREEN
import com.example.pokedexapp.Constants.POKEMON_NAME
import com.example.pokedexapp.pokemondetail.PokemonDetailScreen
import com.example.pokedexapp.pokemonlist.pokemonListScreen
import com.example.pokedexapp.ui.theme.PokedexAppTheme
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokedexAppTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = POKEMON_LIST_SCREEN
                ) {
                    composable(POKEMON_LIST_SCREEN) {
                        // TODO: add composable
                        pokemonListScreen(navController = navController)
                    }
                    composable(
                        route = "$POKEMON_DETAIL_SCREEN/{$DOMINANT_COLOR}/{$POKEMON_NAME}",
                        arguments = listOf(
                            navArgument(
                                DOMINANT_COLOR
                            ) {
                                type = NavType.IntType
                            },
                            navArgument(
                                POKEMON_NAME
                            ) {
                                type = NavType.StringType
                            }
                        )
                    ) {
                        val dominantCol = remember {
                            val color = it.arguments?.getInt(DOMINANT_COLOR)
                            color?.let { Color(it) } ?: Color.White
                        }
                        val pokemonName = remember {
                            it.arguments?.getString(POKEMON_NAME)
                        }
                        // TODO: add composable
                        PokemonDetailScreen(
                            dominantColor = dominantCol,
                            pokemonName = pokemonName?.toLowerCase(Locale.ROOT) ?: "",
                            navController = navController
                        )
                    }
                }
            }
        }
    }
}
