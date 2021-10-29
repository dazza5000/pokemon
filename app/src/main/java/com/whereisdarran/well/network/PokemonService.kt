package com.whereisdarran.well.network

import com.whereisdarran.well.model.Pokemon
import retrofit2.Response
import retrofit2.http.GET

interface PokemonService {
    @GET("pokemon.json")
    suspend fun getPokemon(): Response<List<Pokemon>>
}