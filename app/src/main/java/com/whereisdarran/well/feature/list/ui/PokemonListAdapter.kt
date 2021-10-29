package com.whereisdarran.well.feature.list.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.whereisdarran.well.databinding.PokemonListItemBinding
import com.whereisdarran.well.model.Pokemon

class PokemonListAdapter(private val callback: ((Pokemon) -> Unit)) :
    ListAdapter<Pokemon, PokemonListAdapter.ViewHolder>(DiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            PokemonListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private class DiffCallback : DiffUtil.ItemCallback<Pokemon>() {
        override fun areItemsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
            return oldItem == newItem
        }
    }

    inner class ViewHolder(private val pokemonListItemBinding: PokemonListItemBinding) :
        RecyclerView.ViewHolder(pokemonListItemBinding.root) {

        fun bind(pokemon: Pokemon) {
            with(pokemonListItemBinding) {
                Glide.with(root.context).load(pokemon.imageUrl)
                    .into(pokemonImageView)
                pokemonNameTextView.text = pokemon.name
                pokemonDescriptionTextView.text = pokemon.description

                root.setOnClickListener {
                    callback.invoke(pokemon)
                }
            }
        }
    }

}