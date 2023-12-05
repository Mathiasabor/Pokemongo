package com.abor.myapplication.VUES.Splash

/*
* Ce composant est la page qui s'affiche juste avant de rentrer dans l'appli
* et il sert aussi de background
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
import com.abor.pokemongo.R


@Composable

fun Splash (){

    Box (modifier = Modifier.fillMaxSize()){

        Image(painter = painterResource(id = R.drawable.pokemon_symbole),
            contentDescription ="poke",
            contentScale = ContentScale.FillBounds,

            modifier = Modifier.fillMaxSize()

        )
    }

}


@Composable

fun Splash2 (){

    Box (modifier = Modifier.fillMaxSize()){

        Image(painter = painterResource(id = R.drawable.personagepoke),
            contentDescription ="poke",
            contentScale = ContentScale.FillBounds,

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