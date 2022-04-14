package com.deny.pokemongo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.deny.pokemongo.model.pokemons.PokemonModel
import com.deny.pokemongo.model.pokemons.PokemonRepository

class MainViewModel: ViewModel() {

    var pokemonService: MutableLiveData<PokemonModel>? = null

    fun nextPokemons(auxOffSet: Int) : LiveData<PokemonModel>?{
        pokemonService = PokemonRepository().getPokemons(auxOffSet)
        return pokemonService
    }
}