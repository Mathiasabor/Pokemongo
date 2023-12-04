package com.abor.myapplication.VUES.Splash

/*
* Ce composant est la page qui s'affiche juste avant de rentrer dans l'appli
* et il sert aussi de background
*
* */

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable

import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
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