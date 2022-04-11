package com.deny.pokemongo.model.pokemons

import androidx.lifecycle.MutableLiveData
import com.deny.pokemongo.api.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PokemonRepository {

    var pokemonModel = MutableLiveData<PokemonModel>()

    fun getPokemons(): MutableLiveData<PokemonModel>{

        var retrofit = Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        var service = retrofit.create(ApiService::class.java)
        var call: Call<PokemonModel> = service.getPokemons()

        call.enqueue(object : Callback<PokemonModel> {
            override fun onResponse(call: Call<PokemonModel>, response: Response<PokemonModel>) {
                var pokemons = response.body()
                pokemonModel.value = pokemons!!
            }
            override fun onFailure(call: Call<PokemonModel>, t: Throwable) {
            }
        })
        return pokemonModel
    }
}