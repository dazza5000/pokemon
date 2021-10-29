package com.whereisdarran.well.feature.detail.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.whereisdarran.well.databinding.PokemonDetailFragmentBinding

class PokemonDetailFragment : Fragment() {

    private lateinit var pokemonDetailFragmentBinding: PokemonDetailFragmentBinding
    private val params by navArgs<PokemonDetailFragmentArgs>()
    private val pokemon
        get() = params.pokemon

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        pokemonDetailFragmentBinding =
            PokemonDetailFragmentBinding.inflate(inflater, container, false)
        return pokemonDetailFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.actionBar?.title = pokemon.name
        with(pokemonDetailFragmentBinding) {
            Glide.with(root.context).load(pokemon.imageUrl)
                .into(pokemonImageView)
            pokemonDescriptionTextView.text = pokemon.description
        }
    }
}