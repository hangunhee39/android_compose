package com.example.pokemon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.pokemon.screen.DetailScreen
import com.example.pokemon.screen.MainScreen
import com.example.pokemon.ui.theme.PokemonTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokemonTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TopLevel()
                }
            }
        }
    }
}

@Composable
fun TopLevel(
    navController: NavController = rememberNavController(),
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController as NavHostController,
        startDestination = "Home",
        modifier = modifier
    ){
        composable("Home"){
            MainScreen(onPokemonCLick = {
                val pokemonId= it.substringAfter("pokemon/")
                    .substringBefore("/")
                    .toInt()
                navController.navigate("Detail/${pokemonId}")
            })
        }

        composable(
            "Detail/{pokemonId}",
            //네비게이션 데이터 전달
            arguments = listOf(
                navArgument("pokemonId"){
                    type = NavType.IntType
                }
            )
        ){
            val pokemonId = it.arguments?.getInt("pokemonId") as Int
            DetailScreen(
                pokemonId = pokemonId,
                onUpButtonCLick = {
                    navController.navigate("Home"){
                        popUpTo("Home"){
                            inclusive =true
                        }
                    }
                }
            )
        }
    }
    
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PokemonTheme {
        TopLevel()
    }
}