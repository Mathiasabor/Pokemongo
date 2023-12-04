package com.abor.myapplication.VUES.Welcome
/*
* Ce composant est la page qui affiche la liste des types de pokemons
*
* */

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells

import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle

import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import androidx.navigation.NavController
import com.abor.myapplication.VUES.Splash.Splash
import com.abor.myapplication.VUES.Welcome.Component.PokeType
import com.abor.pokemongo.R

import com.abor.pokemongo.VIEWMODEL.HubViewModel
import kotlinx.coroutines.CoroutineScope




@Composable
fun Welcome(hubViewModel : HubViewModel, scope: CoroutineScope, nav : NavController)
{
    Box(modifier = Modifier.fillMaxSize())
    {
        Splash()
        Column (modifier = Modifier.fillMaxSize())
        {
            val modifier2 = Modifier
                .height(150.dp)
                .padding(5.dp)
                .weight(0.3f)

            Row (horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth())
            {
                Column(modifier = Modifier.padding(20.dp))
                {
                    Text(text = "PokéMoN HUB", fontSize = 30.sp, fontFamily = FontFamily.Cursive, fontWeight = FontWeight.SemiBold, style = TextStyle(brush = Brush.horizontalGradient(
                        listOf(Color.Yellow, Color.Cyan, Color.Red))))
                    Text(text = "PokéMoN Types", fontSize = 20.sp)
                }

                Column(modifier = Modifier.padding(20.dp))
                {
                    Image(painter = painterResource(id = R.drawable.pokeball), contentDescription =null )
                }
            }


            Column(modifier = Modifier.padding(20.dp))
            {
                LazyVerticalGrid(columns = GridCells.Fixed(3) )
                {
                    items(hubViewModel._typePokemon.value.results)
                    {type->
                        PokeType( modifier2, type,nav,hubViewModel,scope )
                    }
                }

            }
        }
    }


}



