package com.whereisdarran.well.feature.list.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.whereisdarran.well.model.Pokemon
import com.whereisdarran.well.network.Network
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PokemonListViewModel : ViewModel() {

    private val _pokemon: MutableStateFlow<PokemonStateFlow> =
        MutableStateFlow(PokemonStateFlow.Success(emptyList()))
    val pokemon: StateFlow<PokemonStateFlow> = _pokemon

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val pokemonResponse = Network.pokemonService.getPokemon()

            if (pokemonResponse.isSuccessful) {
                _pokemon.value = PokemonStateFlow.Success(pokemonResponse.body()!!)
            } else {
                _pokemon.value = PokemonStateFlow.Error(
                    Throwable(
                        pokemonResponse.errorBody()
                            ?.string()
                    )
                )
            }
        }
    }
}

sealed class PokemonStateFlow {
    data class Success(val pokemonList: List<Pokemon>) : PokemonStateFlow()
    data class Error(val exception: Throwable) : PokemonStateFlow()
}
