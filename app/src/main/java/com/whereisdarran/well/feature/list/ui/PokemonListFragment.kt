package com.whereisdarran.well.feature.list.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.whereisdarran.well.data.Result
import com.whereisdarran.well.databinding.PokemonListFragmentBinding
import com.whereisdarran.well.feature.list.viewmodel.PokemonListViewModel
import kotlinx.coroutines.flow.collect


class PokemonListFragment : Fragment() {

    private lateinit var pokemonListFragmentBinding: PokemonListFragmentBinding
    private val pokemonListViewModel: PokemonListViewModel by viewModels()
    private val pokemonListAdapter = PokemonListAdapter { pokemon ->
        findNavController().navigate(
            PokemonListFragmentDirections.showPokemon(pokemon.id),
            navOptions {
                anim {
                    enter = android.R.animator.fade_in
                    exit = android.R.animator.fade_out
                }
            }

        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        pokemonListFragmentBinding = PokemonListFragmentBinding.inflate(inflater, container, false)
        return pokemonListFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pokemonListFragmentBinding.pokemonRecyclerview.adapter = pokemonListAdapter

        lifecycleScope.launchWhenStarted {
            pokemonListViewModel.pokemons.collect {
                when (it) {
                    is Result.Success -> {
                        pokemonListAdapter.submitList(it.data)
                    }
                    else -> {
                        // Would show error/reload action
                        println(it)
                    }
                }
            }
        }
    }
}