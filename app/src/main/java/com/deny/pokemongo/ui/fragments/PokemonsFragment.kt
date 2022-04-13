package com.deny.pokemongo.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.deny.pokemongo.adapter.PokemonAdapter
import com.deny.pokemongo.api.ApiService
import com.deny.pokemongo.databinding.FragmentPokemonsBinding
import com.deny.pokemongo.model.pokemons.PokemonModel

import com.deny.pokemongo.viewmodel.MainViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PokemonsFragment : Fragment() {

    private var _binding: FragmentPokemonsBinding? = null
    private val binding get() = _binding!!

    var auxContador: Int = 0

    lateinit var manager: LinearLayoutManager
    lateinit var pokemonAdapter: PokemonAdapter

    var loading = true
    var visibleItemCount: Int =0
    var totalItemCount: Int =0
    var pastVisiblesItems: Int =0
    

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPokemonsBinding.inflate(inflater, container, false)
        val view = binding.root

        Toast.makeText(requireContext(), "Por favor, aguarde enquanto\n buscamos os dados no servidor", Toast.LENGTH_LONG).show()

        val viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        viewModel.getPokemons()?.observe(viewLifecycleOwner){ value ->

            binding.textViewTotalPokemons.text = value.count.toString()

            // RecyclerView
            manager = binding.recyclerListPokemons.layoutManager as LinearLayoutManager
            pokemonAdapter = PokemonAdapter(value.results)
            binding.recyclerListPokemons.adapter = pokemonAdapter

            // Gambiarra scroll infinity: obs(tentar torna mais dinamico)
            var retrofit = Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()


            binding.recyclerListPokemons.addOnScrollListener(object : RecyclerView.OnScrollListener(){
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)

                    visibleItemCount = manager.childCount
                    totalItemCount = manager.itemCount
                    pastVisiblesItems = manager.findFirstCompletelyVisibleItemPosition()

                    if (loading){
                        if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                            loading = false
                            //Log.e("...", "Last Item Wow !")
                            // Do pagination.. i.e. fetch new data

                            auxContador+=1
                            if(auxContador == 1){

                                var service = retrofit.create(ApiService::class.java)
                                var call: Call<PokemonModel> = service.nextPokemons(500, 500)
                                call.enqueue(object : Callback<PokemonModel> {
                                    override fun onResponse(call: Call<PokemonModel>, response: Response<PokemonModel>) {
                                        var pokemons = response.body()
                                        if (pokemons != null) {
                                            Log.e("...", pokemons.next)
                                            pokemonAdapter.setData(pokemons.results)
                                        }
                                    }
                                    override fun onFailure(call: Call<PokemonModel>, t: Throwable) {
                                    }
                                })
                            }

                            loading = true
                        }
                    }
                }
            })
        }

        binding.buttonProcurarPokemon.setOnClickListener(View.OnClickListener {
            var auxTextProcurarPokemon = binding.textProcurarPokemon.text.toString()
            if (!auxTextProcurarPokemon.isEmpty()){
                val action = PokemonsFragmentDirections.actionPokemonsFragmentToSelectedPokemonFragment(binding.textProcurarPokemon.text.toString())
                findNavController().navigate(action)
            } else{
                Toast.makeText(requireContext(), "Digite alguma coisa", Toast.LENGTH_SHORT).show()
            }
        })

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}