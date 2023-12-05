package com.abor.pokemongo.VUES.ROUTES
/*
* Cette classe défini toutes les routes à utiliser
*
* */

sealed class Routes(val destination : String =""){

    object welcome : Routes("Welcome")

    object splash : Routes("Splash")
    object pokemonDetailPage : Routes("PokemonDetailPage")
}
