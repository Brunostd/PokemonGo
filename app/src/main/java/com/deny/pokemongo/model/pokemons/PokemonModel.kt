package com.deny.pokemongo.model.pokemons

class PokemonModel(
    val count: Int,
    val next: String,
    val previous: String,
    val results: List<MoreResults>
    ) {
}