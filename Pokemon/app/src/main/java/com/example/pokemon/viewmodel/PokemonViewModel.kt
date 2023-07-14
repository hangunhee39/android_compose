package com.example.pokemon.viewmodel

import android.graphics.pdf.PdfDocument.Page
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.PagingState
import androidx.paging.cachedIn
import com.example.pokemon.PokeAPI
import com.example.pokemon.PokemonResponse
import com.example.pokemon.Response
import com.example.pokemon.Result
import com.example.pokemon.Species
import com.example.pokemon.Sprites
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonViewModel @Inject constructor(
    private val pokeAPI: PokeAPI
) : ViewModel() {

    //cachedIn : viewModel 스코프에 맞춰어서 pokemonList에 집어넣게
    val pokemonList: Flow<PagingData<Result>> = getPokemons().cachedIn(viewModelScope)

    var pokemonResult by mutableStateOf(
        PokemonResponse(
            Species(""),
            Sprites("")
        )
    )

    //페이저
    private fun getPokemons(): Flow<PagingData<Result>> = Pager(
        config = PagingConfig(
            pageSize = 20,
            enablePlaceholders = true,
            prefetchDistance = 5
        ),
        pagingSourceFactory = {
            object : PagingSource<Int, Result>() {
                //가장 근처에 있는 위치가 어디인지
                override fun getRefreshKey(state: PagingState<Int, Result>): Int? {
                    return state.anchorPosition
                }

                //
                override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
                    try {
                        val pokemons = if (params.key != null) {
                            //키가 있는 경우
                            pokeAPI.getPokemons(params.key as Int, params.loadSize)
                        } else {
                            //없는 경우 (처음)
                            pokeAPI.getPokemons()
                        }
                        return LoadResult.Page(
                            data = pokemons.results, //현재 페이지 데이터
                            prevKey = pokemons.previous?.substringAfter("offset=")
                                ?.substringBefore("&")?.toInt(),     //이전페이지로 가는 key
                            nextKey = pokemons.next?.substringAfter("offset=")
                                ?.substringBefore("&")?.toInt()       //다음페이지로 가는 key
                        )
                    } catch (e: Exception) {
                        Log.e("E", "error: $e")
                        e.printStackTrace()
                        return LoadResult.Error(e)
                    }
                }

            }
        }
    ).flow


    fun getPokemon(pokemonId: Int) {
        viewModelScope.launch {
            pokemonResult = pokeAPI.getPokemon(pokemonId)
        }
    }
}