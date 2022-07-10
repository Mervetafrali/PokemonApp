package com.mt.pokemonapp.repository

import com.mt.pokemonapp.api.ApiService
import retrofit2.http.Url
import javax.inject.Inject

class ApiRepository
@Inject constructor(private val apiService: ApiService) {

    suspend fun getPokemons() = apiService.getPokemons()


}