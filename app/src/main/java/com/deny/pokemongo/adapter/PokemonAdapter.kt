package com.deny.pokemongo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.deny.pokemongo.R
import com.deny.pokemongo.model.pokemons.MoreResults
import com.deny.pokemongo.ui.fragments.PokemonsFragmentDirections

class PokemonAdapter(var listPokemons: List<MoreResults>): RecyclerView.Adapter<PokemonAdapter.MyViewHolder>() {
    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        lateinit var sendName: String

        fun bind(moreResults: MoreResults){

            var cardPokemons:       CardView = itemView.findViewById(R.id.cardPokemons)
            var textNamePokemons:   TextView = itemView.findViewById(R.id.textNamePokemon)

            var url: String         = moreResults.url
            textNamePokemons.text   = moreResults.name

            cardPokemons.setOnClickListener(View.OnClickListener {
                val action = PokemonsFragmentDirections.actionPokemonsFragmentToSelectedPokemonFragment(moreResults.name)
                sendName = moreResults.name
                itemView.findNavController().navigate(action)
            })
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.pokemons, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(listPokemons[position])
    }

    override fun getItemCount(): Int = listPokemons.size
}