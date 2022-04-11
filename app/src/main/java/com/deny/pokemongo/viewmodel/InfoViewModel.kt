package com.deny.pokemongo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.deny.pokemongo.model.info_pokemon.InfoModel
import com.deny.pokemongo.model.info_pokemon.InfoRepository
import com.deny.pokemongo.model.info_pokemon.MoreAbilities

class InfoViewModel: ViewModel() {

    var infoService: MutableLiveData<InfoModel>? = null

    lateinit var sendNamePokemon: String

    fun getInfoPokemon(sendNamePokemon: String): LiveData<InfoModel>?{
        infoService = InfoRepository().getInfoPokemons(sendNamePokemon)
        return infoService
    }

}