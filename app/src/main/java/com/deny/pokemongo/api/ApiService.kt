package com.deny.pokemongo.api

import com.deny.pokemongo.model.pokemons.PokemonModel
import com.deny.pokemongo.model.info_pokemon.InfoModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("pokemon/")
    fun getPokemons(): Call<PokemonModel>

    @GET("pokemon/{selected}")
    fun getSelectedPokemon(@Path("selected") selectedPokemon: String): Call<InfoModel>

}