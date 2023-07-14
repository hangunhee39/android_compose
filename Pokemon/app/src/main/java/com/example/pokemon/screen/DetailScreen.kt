package com.example.pokemon.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.pokemon.viewmodel.PokemonViewModel

@Composable
fun DetailScreen(
    pokemonId: Int,
    onUpButtonCLick: () -> Unit,
    viewModel: PokemonViewModel = hiltViewModel()
) {
    viewModel.getPokemon(pokemonId)


    Row(
        modifier = Modifier.fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {

        Card(
            elevation = CardDefaults.cardElevation(8.dp),
            modifier = Modifier.padding(8.dp),

            ) {
            val result = viewModel.pokemonResult
            val pokemonName = result.species.name

            Column(horizontalAlignment = Alignment.CenterHorizontally) {

                Text(text = pokemonName)

                AsyncImage(
                    model = result.sprites.frontDefault,
                    contentDescription = pokemonName,
                    modifier = Modifier.size(100.dp)
                )
                Button(onClick = onUpButtonCLick) {
                    Text(text = "위로")
                }
            }
        }
    }
}