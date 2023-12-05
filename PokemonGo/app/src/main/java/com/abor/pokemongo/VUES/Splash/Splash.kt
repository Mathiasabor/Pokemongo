package com.abor.myapplication.VUES.Splash

/*
*Splash 2 affiche sur le détail de chaque page l'image du pokemon en background
* Splash 3 sert de launching page
*
* */

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.abor.pokemongo.R



@Composable

fun Splash2 (url : String){

    Box (modifier = Modifier.fillMaxSize()){


        AsyncImage(
            alignment = Alignment.Center,
            model = url,
            contentDescription = null,
            modifier = Modifier.fillMaxSize()
        )


    }

}
@Composable
fun Splash3 (){

    Box (modifier = Modifier.fillMaxSize()){

        Image(painter = painterResource(id = R.drawable.pokemon_symbole),
            contentDescription ="poke",
            contentScale = ContentScale.FillBounds,

            modifier = Modifier.fillMaxSize()

        )

        CircularProgressIndicator(
            color = Color.Red,
            strokeWidth = 6.dp,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 50.dp)

        )
    }

}