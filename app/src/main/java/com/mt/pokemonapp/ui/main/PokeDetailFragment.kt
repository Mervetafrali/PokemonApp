package com.mt.pokemonapp.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import coil.load
import com.google.firebase.analytics.FirebaseAnalytics
import com.mt.pokemonapp.R
import com.mt.pokemonapp.databinding.FragmentPokeDetailBinding
import com.mt.pokemonapp.service.Overlay
import com.mt.pokemonapp.viewmodel.ApiViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PokeDetailFragment : Fragment(R.layout.fragment_poke_detail) {
    private var _binding: FragmentPokeDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var analytics: FirebaseAnalytics
    private val viewModel: ApiViewModel by viewModels()
    private val args: PokeDetailFragmentArgs by navArgs()
    private lateinit var name: String
    private lateinit var front: String
    private lateinit var back: String
    private lateinit var namePoke: String

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
        analytics = FirebaseAnalytics.getInstance(requireContext())
        setScreen()
        savedInstanceState?.putString("PokemonDetailRequest", "Request")
        analytics.logEvent("FirebaseAnalytics.Param.CONTENT_TYPE", savedInstanceState)
    }

    private fun setScreen() {
        name = args.pokeId
        binding.pokeNameText.text = name
        viewModel.getPokemon(name)
        viewModel.pokemonResponse.observe(requireActivity()) { result ->
            front = result.sprites.front_default
            back = result.sprites.back_default
            namePoke = result.name
            binding.pokeImage.load(result.sprites.other.home.front_default) {
                crossfade(true)
                crossfade(1000)
            }
            binding.pokeNameText.text = result.name
            binding.pokeHeight.text = result.height.toString()
            binding.pokeWeight.text = result.weight.toString()

        }
        binding.overlayButton.setOnClickListener { mView ->
            val intent = Intent(requireContext(), Overlay::class.java)
            intent.putExtra("front", front)
            intent.putExtra("back", back)
            intent.putExtra("name", namePoke)
            requireActivity().startService(intent)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}