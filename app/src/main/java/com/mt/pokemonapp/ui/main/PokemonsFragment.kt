package com.mt.pokemonapp.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.mt.pokemonapp.R
import com.mt.pokemonapp.databinding.FragmentMainBinding
import com.mt.pokemonapp.databinding.FragmentPokemonsBinding
import com.mt.pokemonapp.viewmodel.ApiViewModel

class PokemonsFragment : Fragment(R.layout.fragment_pokemons) {
    private var _binding:FragmentPokemonsBinding?=null
    private val binding get()=_binding!!
    private val viewModel: ApiViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPokemonsBinding.inflate(
            inflater, container, false
        )
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       // setScreen()
    }
    private fun setScreen() {

        viewModel.pokemonsResponse.observe(requireActivity()) { result ->
            Log.i("deneme", result.results.toString())
        }
        viewModel.getPokemon("https://pokeapi.co/api/v2/pokemon/1/")
        viewModel.pokemonResponse.observe(requireActivity()) { result ->
            Log.i("deneme2", result.toString())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}