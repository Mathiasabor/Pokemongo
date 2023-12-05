package com.abor.pokemongo.VIEWMODEL

import androidx.annotation.Nullable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abor.pokemongo.MODEL.API.ApiClient
import com.abor.pokemongo.MODEL.API.Home

import com.abor.pokemongo.MODEL.API.Other
import com.abor.pokemongo.MODEL.API.Poked
import com.abor.pokemongo.MODEL.API.PokemonDetail
import com.abor.pokemongo.MODEL.API.Pokemons
import com.abor.pokemongo.MODEL.API.Sprites
import com.abor.pokemongo.MODEL.API.TypElement
import com.abor.pokemongo.MODEL.API.TypePokemon
import com.abor.pokemongo.MODEL.API.move
import kotlinx.coroutines.Dispatchers

import kotlinx.coroutines.async
import java.lang.Exception

class HubViewModel : ViewModel() {

//ici ce sont des variables state qui seront connectés aux vues

    var typePokemon = mutableStateOf(TypePokemon(0, Nullable(),
        Nullable(), mutableListOf(TypElement("water","b"))))
    val _typePokemon : State<TypePokemon> = typePokemon


    var pokemonsbytypelist = mutableStateOf(mutableListOf<Pokemons>())
    val _pokemonsbytypelist:State<MutableList<Pokemons>> = pokemonsbytypelist
    var detailPokemon = mutableStateOf(
        PokemonDetail(
            1,
            "",
            1,
            1,
            mutableListOf(move(TypElement("",""))),
            TypElement("",""),
            Sprites(Other(Home(""))),
            1
        )
    )
    val _detailPokemon : State<PokemonDetail> = detailPokemon



    //ici une variable state lié au dégradé de chaque type qui se trouve dans ColorType
    var horizontalGradient = mutableStateOf("normal")
    val _horizontalGradient : State<String> =horizontalGradient

    //ici ce sont les fonctions lié à l'appel de l'API
    suspend fun executeGetAllPokemonType() : TypePokemon {
        return viewModelScope.async(context = Dispatchers.IO){
            lateinit var typePokemonList: TypePokemon
            try {
                val response = ApiClient.apiService.getAllPokemonType()
                if(response.isSuccessful && response.body()!=null){

                    typePokemonList = response.body()!!
                }

            }catch (e : Exception){
                println(e.message)
            }

            return@async typePokemonList
        }.await()
    }



    suspend fun executeGetAllPokemonByType(id : Int): Pokemons {
        return viewModelScope.async(context = Dispatchers.IO){
            lateinit  var pokemonListByType : Pokemons
            try {
                val response = ApiClient.apiService.getAllPokemonByType(id)
                if(response.isSuccessful && response.body()!=null){
                    pokemonListByType = response.body()!!
                }

            }catch (e : Exception){
                println(e.message)
            }

            return@async pokemonListByType
        }.await()
    }


    suspend fun executeGetPokemonById(id : Int) : PokemonDetail {
        return viewModelScope.async(context = Dispatchers.IO){
            lateinit  var pokemonDetail : PokemonDetail

            try {
                val response = ApiClient.apiService.getPokemonById(id)
                if(response.isSuccessful && response.body()!=null){
                    pokemonDetail = response.body()!!
                }

            }catch (e : Exception){
                println(e.message)
            }

            return@async pokemonDetail
        }.await()


    }
//ici c'est la fonction pour extraire l'id de l'url
    fun extraireEntier(chaine: String): Int? {
        val regex = Regex("""/(\d+)/$""")
        val matchResult = regex.find(chaine)

        return matchResult?.groupValues?.get(1)?.toIntOrNull()
    }
}