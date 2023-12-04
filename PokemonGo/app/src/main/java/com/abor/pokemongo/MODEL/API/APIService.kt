package com.abor.pokemongo.MODEL.API


import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path


interface ApiService {
//récupérer les types pokemons
@GET("type/")
suspend fun getAllPokemonType() : Response<TypePokemon>
//récupére les pokemons par type
@GET("type/{id}")
suspend fun getAllPokemonByType(@Path("id") id : Int): Response<Pokemons>
//récupérer le détail de chaque pokémon
@GET("pokemon/{id}")
suspend fun getPokemonById(@Path("id") id : Int): Response<PokemonDetail>


}