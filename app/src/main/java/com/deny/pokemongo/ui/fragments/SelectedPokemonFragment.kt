package com.deny.pokemongo.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.deny.pokemongo.R
import com.deny.pokemongo.adapter.AbilitiesAdapter
import com.deny.pokemongo.adapter.StatsAdapter
import com.deny.pokemongo.api.ApiService
import com.deny.pokemongo.databinding.FragmentSelectedPokemonBinding
import com.deny.pokemongo.model.info_pokemon.InfoModel
import com.deny.pokemongo.model.info_pokemon.MoreAbilities
import com.deny.pokemongo.viewmodel.InfoViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class SelectedPokemonFragment : Fragment() {

    private var _binding: FragmentSelectedPokemonBinding? = null
    private val binding get() = _binding!!

    val args: SelectedPokemonFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSelectedPokemonBinding.inflate(inflater, container, false)
        val view = binding.root

        Toast.makeText(requireContext(), "Por favor, aguarde enquanto\n buscamos os dados no servidor", Toast.LENGTH_LONG).show()

        val infoViewModel = ViewModelProvider(this).get(InfoViewModel::class.java)

        infoViewModel.getInfoPokemon(args.receiveNamePokemon)?.observe(viewLifecycleOwner){ value ->
            //Segue a mesma sequencia do XML
            binding.textViewBaseExperience.text = value.base_experience.toString()
            binding.textViewAltura.text = value.height.toString()
            binding.textViewPeso.text = value.weight.toString()
            binding.recyclerViewAbility.adapter = AbilitiesAdapter(value.abilities)
            binding.recyclerViewStats.adapter = StatsAdapter(value.stats)
        }

        binding.textViewReceiveNamePokemon.text = args.receiveNamePokemon

        binding.buttonBackHomeScreen.setOnClickListener(View.OnClickListener {
            findNavController().navigate(R.id.action_selectedPokemonFragment_to_pokemonsFragment)
        })

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}