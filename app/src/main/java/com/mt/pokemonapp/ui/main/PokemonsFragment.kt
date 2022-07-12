package com.mt.pokemonapp.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.mt.pokemonapp.R
import com.mt.pokemonapp.adapter.PokemonsAdapter
import com.mt.pokemonapp.databinding.FragmentPokemonsBinding
import com.mt.pokemonapp.viewmodel.ApiViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PokemonsFragment : Fragment(R.layout.fragment_pokemons) {
    private var _binding:FragmentPokemonsBinding?=null
    private val binding get()=_binding!!
    private val viewModel: ApiViewModel by viewModels()
    private lateinit var pokemonsAdapter: PokemonsAdapter
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
        setScreen()
    }
    private fun setScreen() {
        pokemonsAdapter= PokemonsAdapter()
        binding.poke.apply {
            layoutManager = GridLayoutManager(activity, 1)
            setHasFixedSize(true)
            adapter = pokemonsAdapter
        }
        viewModel.pokemonsResponse.observe(requireActivity()) { result ->
            pokemonsAdapter.pokeDetails=result.results
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}