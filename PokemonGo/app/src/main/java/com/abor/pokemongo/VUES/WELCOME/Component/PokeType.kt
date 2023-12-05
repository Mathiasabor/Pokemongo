package com.abor.myapplication.VUES.Welcome.Component


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth


import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.abor.pokemongo.MODEL.API.Poked

import com.abor.pokemongo.VIEWMODEL.HubViewModel

import com.abor.pokemongo.VUES.ROUTES.Routes
import com.abor.pokemongo.VUES.WELCOME.Component.ColorType
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/*
* Ce composant poketype va afficher l'aperçu des types des pokemon
*
* */





@Composable

fun PokeType2( name : String ,modifier2 : Modifier, poked : Poked, nav : NavController, hubViewModel: HubViewModel, scope : CoroutineScope){

    val id= hubViewModel.extraireEntier(poked.pokemon.url)
    val url = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/home/${id}.png"

    ElevatedButton(

        onClick = {
            scope.launch {
                hubViewModel.horizontalGradient.value = name
                hubViewModel.detailPokemon.value = hubViewModel.executeGetPokemonById(hubViewModel.extraireEntier(poked.pokemon.url)!!)
                nav.navigate(Routes.pokemonDetailPage.destination){
                    popUpTo(Routes.welcome.destination)
                }
            }
        },
        contentPadding = PaddingValues(),
        shape = RoundedCornerShape(15.dp),

        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
        modifier = modifier2.background(shape = RoundedCornerShape(15.dp),brush = Brush.horizontalGradient(ColorType.verticalGradients[name]!!))


    )
    {

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
        )
        {
            AsyncImage(
                alignment = Alignment.Center,
                model = url,
                contentDescription = null,
                modifier = Modifier.fillMaxWidth()
            )

            Text(text = poked.pokemon.name, fontSize = 20.sp, color = Color.White)



        }




    }
}
