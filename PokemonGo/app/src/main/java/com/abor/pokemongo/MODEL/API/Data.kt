package com.abor.pokemongo.MODEL.API

import androidx.annotation.Nullable
import com.google.gson.annotations.SerializedName


//cette partie représente la liste des types de tous les pokemons
data class TypePokemon(
    val count : Int,
    val next : Nullable,
    val previous : Nullable,
    val results : MutableList<TypElement>

)

data class TypElement(
    @SerializedName("name")  val name : String,
    @SerializedName("url") val url : String,
)


//cette partie représente la liste des pokemon retourné par Type
data class Pokemons(
   @SerializedName("pokemon") val pokemon: List<Poked>
)

data class Poked(
    @SerializedName("pokemon")  val pokemon : TypElement,
    @SerializedName("slot") val slot : Int

)



//cette partie suivante correspond au détail du pokemon

data class PokemonDetail(
    val base_experience : Int,
    val name : String,
    val order : Int,
    val height : Int,
    val moves : MutableList<move>,
    val species : TypElement,
    val sprites : Sprites,
    val weight : Int,

)

data class Sprites(
    val other : Other
)

data class Other(
    @SerializedName("official-artwork") val official_artwork : Official_artwork
)

data class Official_artwork(
    val front_shiny : String

)

data class move(
    val move : TypElement,
)