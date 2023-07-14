package com.example.pokemon.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.example.pokemon.TopLevel
import com.example.pokemon.ui.theme.PokemonTheme
import com.example.pokemon.viewmodel.PokemonViewModel

@Composable
fun MainScreen(
    onPokemonCLick: (String) -> Unit,
    viewModel: PokemonViewModel = hiltViewModel()
) {
    //collectAsLazyPagingItems : compose에 쓰기위해서
    val items = viewModel.pokemonList.collectAsLazyPagingItems()
    LazyColumn {
        items(
            items,
            key = { it.url }
        ) {
            it?.let {
                Card(
                    elevation = CardDefaults.cardElevation(8.dp),
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxSize()
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Column(
                            modifier = Modifier.weight(3f)
                        ) {
                            Text(text = "포켓몬: ${it.name}")
                            Text(
                                text = it.url,
                                Modifier.alpha(0.4f),
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                        Spacer(modifier = Modifier.size(8.dp))
                        Button(
                            modifier = Modifier.weight(1f),
                            onClick = { onPokemonCLick(it.url) }) {
                            Text(text = "보기")
                        }
                    }
                }
            }
        }
    }
}
