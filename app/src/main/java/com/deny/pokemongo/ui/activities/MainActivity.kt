package com.deny.pokemongo.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.deny.pokemongo.databinding.ActivityMainBinding
import retrofit2.Retrofit

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    lateinit var retrofit: Retrofit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root

        //binding.textHello.text = "E ai galera"

        /*retrofit = Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        getPokemons()*/

        setContentView(view)
    }

    /*fun getPokemons(){
        var service = retrofit.create(ApiService::class.java)
        var call: Call<PokemonModel> = service.getPokemons()

        call.enqueue(object : Callback<PokemonModel>{
            override fun onResponse(call: Call<PokemonModel>, response: Response<PokemonModel>) {
                var pokemons = response.body()

                binding.textHello.text = pokemons?.count.toString()

                Toast.makeText(baseContext, "Sucesos", Toast.LENGTH_LONG).show()
            }

            override fun onFailure(call: Call<PokemonModel>, t: Throwable) {
                Toast.makeText(baseContext, "Falha ao puxar", Toast.LENGTH_LONG).show()
            }
        })
    }*/
}