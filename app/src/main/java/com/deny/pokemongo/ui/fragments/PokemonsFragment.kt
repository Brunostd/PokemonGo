package com.deny.pokemongo.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.deny.pokemongo.adapter.PokemonAdapter
import com.deny.pokemongo.databinding.FragmentPokemonsBinding

import com.deny.pokemongo.viewmodel.MainViewModel

class PokemonsFragment : Fragment() {

    private var _binding: FragmentPokemonsBinding? = null
    private val binding get() = _binding!!

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
            binding.recyclerListPokemons.adapter = PokemonAdapter(value.results)
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