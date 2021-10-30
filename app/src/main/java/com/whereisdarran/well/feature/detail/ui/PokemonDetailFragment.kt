package com.whereisdarran.well.feature.detail.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.whereisdarran.well.data.Result
import com.whereisdarran.well.databinding.PokemonDetailFragmentBinding
import com.whereisdarran.well.feature.detail.viewmodel.PokemonDetailViewModel
import kotlinx.coroutines.flow.collect

class PokemonDetailFragment : Fragment() {

    private lateinit var pokemonDetailFragmentBinding: PokemonDetailFragmentBinding
    private val params by navArgs<PokemonDetailFragmentArgs>()
    private val pokemonDetailViewModel: PokemonDetailViewModel by viewModels()

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

        lifecycleScope.launchWhenStarted {
            pokemonDetailViewModel.getPokemon(params.id).collect {
                when (it) {
                    is Result.Success -> {
                        val pokemon = it.data
                        (activity as AppCompatActivity?)?.supportActionBar?.title = pokemon.name
                        with(pokemonDetailFragmentBinding) {
                            Glide.with(root.context).load(pokemon.imageUrl)
                                .into(pokemonImageView)
                            pokemonDescriptionTextView.text = pokemon.description
                        }
                    }
                    else -> {
                        println(it)
                    }
                }
            }
        }
    }
}