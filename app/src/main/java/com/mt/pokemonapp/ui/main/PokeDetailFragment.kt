package com.mt.pokemonapp.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.mt.pokemonapp.R
import com.mt.pokemonapp.adapter.PokemonsAdapter
import com.mt.pokemonapp.databinding.FragmentPokeDetailBinding
import com.mt.pokemonapp.databinding.FragmentPokemonsBinding
import com.mt.pokemonapp.viewmodel.ApiViewModel


class PokeDetailFragment : Fragment(R.layout.fragment_poke_detail) {
    private var _binding:FragmentPokeDetailBinding?=null
    private val binding get()=_binding!!
    private val viewModel: ApiViewModel by viewModels()
    private val args: PokeDetailFragmentArgs by navArgs()
    private lateinit var name:String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPokeDetailBinding.inflate(
            inflater, container, false
        )
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setScreen()
    }
    private fun setScreen() {
        name=args.pokeId
        binding.pokeNameText.text=name
        /*viewModel.getPokemon("https://pokeapi.co/api/v2/pokemon/1/")
        viewModel.pokemonResponse.observe(requireActivity()) { result ->
            Log.i("deneme2", result.toString())
        }*/

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}