package com.deny.pokemongo.adapter

import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.deny.pokemongo.R
import com.deny.pokemongo.model.info_pokemon.MoreStats

class StatsAdapter(var listaStats: List<MoreStats>): RecyclerView.Adapter<StatsAdapter.MyViewHolder>() {
    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(moreStats: MoreStats){

            var textAtributo: TextView = itemView.findViewById(R.id.textViewAtributo)
            var textStatusPokemon: TextView = itemView.findViewById(R.id.textViewStatusPokemon)
            var textEffort: TextView = itemView.findViewById(R.id.textViewEffort)

            textAtributo.text = moreStats.stat.name
            textStatusPokemon.text = moreStats.base_stat.toString()
            textEffort.text = moreStats.effort.toString()

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.selected_pokemons_stats, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(listaStats[position])
    }

    override fun getItemCount(): Int = listaStats.size
}