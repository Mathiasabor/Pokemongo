package com.abor.pokemongo.VUES.ROUTES
/*
* Cette classe défini toutes les routes à utiliser
*
* */

sealed class Routes(val destination : String =""){

    object welcome : Routes("Welcome")
    object pokemonPage : Routes("PokemonsPage")
    object splash : Routes("Splash")
}
