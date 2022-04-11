package com.deny.pokemongo.model.info_pokemon

import androidx.lifecycle.MutableLiveData
import com.deny.pokemongo.api.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class InfoRepository {

    //var sendNamePokemon = "bulbasaur"
    var infoModel = MutableLiveData<InfoModel>()

    fun getInfoPokemons(sendNamePokemon: String): MutableLiveData<InfoModel>{

        var retrofit = Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        var service = retrofit.create(ApiService::class.java)
        var call: Call<InfoModel> = service.getSelectedPokemon(sendNamePokemon)

        call.enqueue(object : Callback<InfoModel>{
            override fun onResponse(call: Call<InfoModel>, response: Response<InfoModel>) {
                if(response.isSuccessful){
                    var pokemons = response.body()
                    infoModel.value = pokemons!!
                } else{
                    var statsFailure: List<MoreStats> = arrayListOf()
                    var abilitiesFailure: List<MoreAbilities> = arrayListOf()
                    var infoFailure: InfoModel = InfoModel(abilitiesFailure, 0, statsFailure, 0, 0)
                    infoModel.postValue(infoFailure)
                }
            }
            override fun onFailure(call: Call<InfoModel>, t: Throwable) {
            }

        })
        return infoModel
    }
}