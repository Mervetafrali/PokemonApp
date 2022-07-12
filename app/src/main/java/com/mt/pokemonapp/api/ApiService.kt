package com.mt.pokemonapp.api

import com.mt.pokemonapp.helper.Constants.END_POINT_REPO
import com.mt.pokemonapp.model.Pokemon
import com.mt.pokemonapp.model.Pokemons
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiService {

    @GET(END_POINT_REPO)
    suspend fun getPokemons(): Response<Pokemons>

    @GET
    suspend fun getPokemon(@Url url: String): Response<Pokemon>


}