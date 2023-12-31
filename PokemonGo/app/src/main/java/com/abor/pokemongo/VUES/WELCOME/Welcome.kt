package com.abor.myapplication.VUES.Welcome
/*
* Ce composant est la page qui affiche la liste des pokemons par types
*
* */

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow

import androidx.compose.foundation.lazy.items

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable


import androidx.compose.ui.Modifier

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource


import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import androidx.navigation.NavController


import com.abor.myapplication.VUES.Welcome.Component.PokeType2

import com.abor.pokemongo.R

import com.abor.pokemongo.VIEWMODEL.HubViewModel
import kotlinx.coroutines.CoroutineScope



@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun Welcome(hubViewModel : HubViewModel, scope: CoroutineScope, nav : NavController)
{
    Box(modifier = Modifier.fillMaxSize())
    {

        Column (modifier = Modifier.fillMaxSize())
        {
            val modifier2 = Modifier
                .height(250.dp)
                .padding(5.dp)
                .weight(0.3f)

            Row (horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .background(color = Color.Red)
                    .fillMaxWidth())
            {
                Column(modifier = Modifier.padding(20.dp))
                {
                    Text(text = "Poké", fontSize = 30.sp, fontFamily = FontFamily.Cursive, fontWeight = FontWeight.SemiBold, color = Color.White)

                }

                Column(modifier = Modifier.padding(20.dp))
                {
                    Image(painter = painterResource(id = R.drawable.pokeball128), contentDescription =null )
                }
            }


            Column(modifier = Modifier

                .padding(start = 20.dp, end = 20.dp))
            {

                LazyColumn{
                    items(hubViewModel._pokemonsbytypelist.value)
                    {pokemons->


                        Row (horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.padding(top = 5.dp).fillMaxWidth()
                        ){

                            Text(text = pokemons.name, fontWeight = FontWeight.SemiBold, fontSize = 20.sp, color =Color.Black)
                            Text(text = "${pokemons.pokemon.size}", fontWeight = FontWeight.SemiBold, fontSize = 20.sp, color =Color.Black)

                        }


                        LazyRow{
                            items(pokemons.pokemon){poked->
                                PokeType2(
                                    name = pokemons.name,
                                    modifier2 = modifier2,
                                    poked = poked ,
                                    nav = nav,
                                    hubViewModel = hubViewModel,
                                    scope = scope
                                )
                            }
                        }

                    }
                }

            }



        }
    }


}



