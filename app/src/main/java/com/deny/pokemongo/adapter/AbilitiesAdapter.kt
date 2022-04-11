package com.deny.pokemongo.adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.deny.pokemongo.R
import com.deny.pokemongo.model.info_pokemon.Ability
import com.deny.pokemongo.model.info_pokemon.MoreAbilities

class AbilitiesAdapter(var listaAbility: List<MoreAbilities>): RecyclerView.Adapter<AbilitiesAdapter.MyViewHolder>() {
    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(moreAbilities: MoreAbilities){

            var abilitiName: TextView = itemView.findViewById(R.id.textViewAbilityName)
            var slot: TextView = itemView.findViewById(R.id.textViewSlot)

            abilitiName.text = moreAbilities.ability.name
            slot.text = moreAbilities.slot.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.selected_pokemon_ablities, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(listaAbility[position])
    }

    override fun getItemCount(): Int = listaAbility.size
}