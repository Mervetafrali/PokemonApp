package com.mt.pokemonapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mt.pokemonapp.databinding.PokemonsDetailAdapterBinding
import com.mt.pokemonapp.model.Result
import com.mt.pokemonapp.ui.main.PokemonsFragmentDirections

class PokemonsAdapter : PagingDataAdapter<Result, PokemonsAdapter.MyViewHolder>(DifferCallback){
    inner class MyViewHolder(val  binding:PokemonsDetailAdapterBinding):
        RecyclerView.ViewHolder(binding.root)

    object DifferCallback :DiffUtil.ItemCallback<Result>(){
        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem==newItem
        }

        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem==newItem
        }

    }
    private val differ= AsyncListDiffer(this,DifferCallback)
    var pokeDetails:List<Result>
        get() = differ.currentList
        set(value){
            differ.submitList(value)
        }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonsAdapter.MyViewHolder {
        return MyViewHolder(
            PokemonsDetailAdapterBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: PokemonsAdapter.MyViewHolder, position: Int) {
        val currentItem =pokeDetails[position]
        holder.binding.apply {
            pokeNameText.text=currentItem.name
        }
        holder.binding.pokeNameText.setOnClickListener { mView ->
            val direction =
                PokemonsFragmentDirections.actionPokemonsFragmentToPokeDetailFragment(currentItem.url)
            mView.findNavController().navigate(direction)
        }
    }

    override fun getItemCount()=pokeDetails.size

}