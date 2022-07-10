package com.mt.pokemonapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mt.pokemonapp.model.Pokemon
import com.mt.pokemonapp.model.Pokemons
import com.mt.pokemonapp.repository.ApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.http.Url
import javax.inject.Inject

@HiltViewModel
class ApiViewModel
@Inject
constructor(private val pokemons:ApiRepository): ViewModel(){
    private val _response= MutableLiveData<Pokemons>()
    val pokemonsResponse:LiveData<Pokemons>
        get()=_response
    private val _responsePoke=MutableLiveData<Pokemon>()
    val pokemonResponse:LiveData<Pokemon>
        get()=_responsePoke
    init {
        getPokemons()
    }
    fun  getPokemon(url:String){
        getPoke(url)
    }
    private fun getPokemons()=viewModelScope.launch {
        pokemons.getPokemons().let { response->
            if (response.isSuccessful){
                _response.postValue(response.body())
            }else{
                Log.d("Response", "getPokemons: ${response.code()}")
            }
        }
    }
    private fun getPoke(@Url url:String) = viewModelScope.launch {
        pokemons.getPokemon(url).let { response ->
            if (response.isSuccessful) {
                _responsePoke.postValue(response.body())
            } else {
                Log.d("Response", "getUser: ${response.code()}")
            }
        }
    }
}