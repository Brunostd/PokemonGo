package com.deny.pokemongo.model.info_pokemon

class InfoModel(
    val abilities: List<MoreAbilities>,
    val base_experience: Int,
    val stats: List<MoreStats>,
    val height: Int,
    val weight: Int
) {
}