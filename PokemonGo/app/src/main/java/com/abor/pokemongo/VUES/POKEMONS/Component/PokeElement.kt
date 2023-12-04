package com.abor.pokemongo.VUES.POKEMONS.Component

/*
* Ce composant PokeElement va afficher l'aperçu des détails du pokemon
*
* */


import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandIn
import androidx.compose.animation.shrinkOut
import androidx.compose.foundation.background

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size

import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon

import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.abor.pokemongo.MODEL.API.Official_artwork
import com.abor.pokemongo.MODEL.API.Other
import com.abor.pokemongo.MODEL.API.Poked
import com.abor.pokemongo.MODEL.API.PokemonDetail
import com.abor.pokemongo.MODEL.API.Sprites
import com.abor.pokemongo.MODEL.API.TypElement
import com.abor.pokemongo.MODEL.API.move
import com.abor.pokemongo.VIEWMODEL.HubViewModel

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import com.abor.pokemongo.R
import com.abor.pokemongo.VUES.WELCOME.Component.ColorType

//cet objet sert à mettre detailpokemon à afficher
object PokeElement {
    var detailPokemon = mutableStateOf(
        PokemonDetail(
            1,
            "",
            1,
            1,
            mutableListOf(move(TypElement("",""))),
            TypElement("",""),
            Sprites(Other(Official_artwork(""))),
            1
        )
    )

    val _detailPokemon : State<PokemonDetail> = detailPokemon
}

@SuppressLint("UnrememberedMutableState")
@Composable
fun PokeElement(poked : Poked, hubViewModel: HubViewModel, scope : CoroutineScope)
{
    val expanded = remember{ mutableStateOf(false) }

    Column (modifier = Modifier.fillMaxWidth())
    {
        Button(
            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
            shape = RoundedCornerShape(20.dp),
            onClick = {
                scope.launch{
                    PokeElement.detailPokemon.value = hubViewModel.executeGetPokemonById(hubViewModel.extraireEntier(poked.pokemon.url)!!)
                    if (expanded.value) expanded.value = false else expanded.value = true

                }
            },

            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Brush.horizontalGradient(ColorType.verticalGradients[hubViewModel._horizontalGradient.value]!!),
                    shape = RoundedCornerShape(20.dp)
                )

        )
        {

            Column (modifier = Modifier.fillMaxWidth())
            {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp, top = 20.dp, bottom = 20.dp)
                ){
                    Text(text = poked.pokemon.name, fontSize = 30.sp, fontFamily = FontFamily.Serif, color = Color.White)
                    Icon(imageVector = if(expanded.value) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown, contentDescription ="", modifier = Modifier.size(40.dp) )

                }

                AnimatedVisibility(
                    expanded.value,
                    enter = expandIn(expandFrom = Alignment.TopStart),
                    exit = shrinkOut(shrinkTowards = Alignment.BottomEnd)

                )
                {

                    Surface(
                        color = Color.Transparent,
                        shape = RectangleShape,
                        modifier = Modifier.fillMaxWidth()
                    )
                    {
                        Column {
                            Row {
                                Card(
                                    shape = RoundedCornerShape(20.dp),
                                    modifier = Modifier
                                        .height(150.dp)
                                        .padding(end = 10.dp)
                                        .weight(0.2f))
                                {

                                    AsyncImage(
                                        alignment = Alignment.Center,
                                        model = PokeElement._detailPokemon.value.sprites.other.official_artwork.front_shiny,
                                        contentDescription = null,
                                        modifier = Modifier.fillMaxSize()
                                    )

                                }

                                Card(
                                    shape = RoundedCornerShape(20.dp),
                                    modifier = Modifier
                                        .height(150.dp)
                                        .padding(start = 10.dp)
                                        .weight(0.4f))
                                {

                                    Column (modifier = Modifier.padding(20.dp))
                                    {
                                        Text(text = poked.pokemon.name, fontSize = 20.sp, color = Color.White)
                                        Text(text = "Species : ${PokeElement._detailPokemon.value.species.name}", color = Color.White)
                                        Text(text = "Order : ${PokeElement._detailPokemon.value.order}", color = Color.White)

                                    }

                                }

                            }


                            Card(
                                shape = RoundedCornerShape(20.dp),
                                modifier = Modifier
                                    .height(150.dp)
                                    .padding(top = 10.dp)
                                    .fillMaxWidth())
                            {



                                Row (verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    modifier = Modifier
                                        .padding(20.dp)
                                        .fillMaxSize())
                                {

                                    Column (
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        verticalArrangement = Arrangement.Center)
                                    {
                                            Text(text = "Height", color= Color.White)
                                        Spacer(modifier = Modifier.size(10.dp))
                                            Icon(painter = painterResource(id = R.drawable.hauteur_du_texte256), contentDescription ="height",modifier = Modifier.size(50.dp) , tint = Color.White)
                                            Text(text = "${PokeElement._detailPokemon.value.height}", color = Color.White)
                                    }

                                    Column (
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        verticalArrangement = Arrangement.Center)
                                    {
                                        Text(text = "Weight", color= Color.White)
                                        Spacer(modifier = Modifier.size(10.dp))
                                        Icon(painter = painterResource(id = R.drawable.echelle_de_poids256), contentDescription ="weight" ,modifier = Modifier.size(50.dp), tint = Color.White)
                                        Text(text = "${PokeElement._detailPokemon.value.weight}", color = Color.White)
                                    }

                                    Column (
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        verticalArrangement = Arrangement.Center)
                                    {
                                        Text(text = "Experience", color= Color.White)
                                        Spacer(modifier = Modifier.size(10.dp))
                                        Icon(painter = painterResource(id = R.drawable.experience256), contentDescription ="base_experience", modifier = Modifier.size(50.dp) , tint = Color.White)
                                        Text(text = "${PokeElement._detailPokemon.value.base_experience}", color = Color.White)
                                    }

                                }

                            }

                            Card(
                                shape = RoundedCornerShape(20.dp),
                                modifier = Modifier
                                    .height(150.dp)
                                    .padding(top = 10.dp)
                                    .fillMaxWidth())
                            {
                                Column (verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally,

                                    modifier = Modifier.fillMaxSize()){
                                    Text(text = "Moves", color= Color.White)
                                    Spacer(modifier = Modifier.size(10.dp))
                                    LazyRow {
                                        items(PokeElement._detailPokemon.value.moves)
                                        {move->
                                            Column (horizontalAlignment = Alignment.CenterHorizontally,
                                                    verticalArrangement = Arrangement.Center,
                                                    modifier = Modifier.padding(start = 20.dp, end = 20.dp, bottom = 20.dp))
                                            {
                                                    Icon(painter = painterResource(id = R.drawable.combat_de_puissance), contentDescription ="height",modifier = Modifier.size(50.dp), tint = Color.White )
                                                    Text(text = move.move.name, color = Color.White)
                                            }

                                        }
                                    }
                                }
                            }

                        }
                    }

                }

            }

        }
        Spacer(modifier = Modifier.padding(10.dp))
    }


}

