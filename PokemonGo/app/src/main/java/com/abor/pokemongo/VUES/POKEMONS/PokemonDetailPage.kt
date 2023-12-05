package com.abor.pokemongo.VUES.POKEMONS

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.abor.myapplication.VUES.Splash.Splash2
import com.abor.pokemongo.R
import com.abor.pokemongo.VIEWMODEL.HubViewModel
import com.abor.pokemongo.VUES.POKEMONS.Component.PokeElement
import com.abor.pokemongo.VUES.ROUTES.Routes

/*
* ici c'est la page qui affiche le détail de chaque pokemon
* */
@Composable

fun PokemonDetailPage( Nav : NavController, hubViewModel: HubViewModel)
{
    Box (modifier = Modifier.fillMaxSize())
    {
        Splash2(hubViewModel._detailPokemon.value.sprites.other.home.front_default)

        Column {

            Row (horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .background(color = Color.Red)
                    .fillMaxWidth()
            ){
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(20.dp)
                )
                {

                   Row {
                       IconButton(onClick = { Nav.navigate(Routes.welcome.destination){
                           popUpTo(Routes.welcome.destination) }
                       })
                       {
                           Image(painter = painterResource(id = R.drawable.en_arriere), contentDescription ="Back" )
                       }

                       Text(text = "Back", fontSize = 20.sp, modifier = Modifier.padding(start = 5.dp, top = 10.dp), color = Color.White, fontFamily = FontFamily.Cursive, fontWeight = FontWeight.SemiBold)

                   }
                }

                Column(horizontalAlignment = Alignment.End, modifier = Modifier.padding(20.dp)
                )
                {
                    Text(text = hubViewModel._horizontalGradient.value, fontSize = 30.sp, fontFamily = FontFamily.Cursive, fontWeight = FontWeight.SemiBold, color = Color.White)
                }

            }

            Column ( modifier = Modifier.padding(20.dp))
            {
                PokeElement(hubViewModel)
            }

        }

    }

}