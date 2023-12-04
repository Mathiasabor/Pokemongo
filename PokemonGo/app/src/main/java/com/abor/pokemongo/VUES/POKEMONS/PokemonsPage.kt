package com.abor.pokemongo.VUES.POKEMONS

/*
* Ce composant est la page qui affiche la liste des pokemons par type
*
* */


import androidx.compose.foundation.Image
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.abor.myapplication.VUES.Splash.Splash
import com.abor.pokemongo.R

import com.abor.pokemongo.VIEWMODEL.HubViewModel
import com.abor.pokemongo.VUES.POKEMONS.Component.PokeElement
import com.abor.pokemongo.VUES.ROUTES.Routes
import kotlinx.coroutines.CoroutineScope


@Composable
fun PokemonsPage(scope: CoroutineScope, Nav : NavController, hubViewModel: HubViewModel){

    Box (modifier = Modifier.fillMaxSize())
    {
        Splash()

        Column {

            Row (horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
                ){
                Column(

                    modifier = Modifier
                        .padding(20.dp)
                        )
                {

                    IconButton(onClick = { Nav.navigate(Routes.welcome.destination){
                        popUpTo(Routes.welcome.destination)
                    }
                    }) {
                        Image(painter = painterResource(id = R.drawable.accueil), contentDescription =null )
                    }
                }

                Column(
                    horizontalAlignment = Alignment.End,
                    modifier = Modifier
                        .padding(20.dp)
                        )
                {
                    Text(text = hubViewModel._horizontalGradient.value, fontSize = 30.sp, fontFamily = FontFamily.Cursive, fontWeight = FontWeight.SemiBold)
                    Text(text = "${hubViewModel._horizontalGradient.value} PokéMoN List", fontSize = 20.sp)
                }

            }


            LazyColumn(modifier = Modifier.padding(20.dp))
            {
                items(hubViewModel._byTypePokemon.value.pokemon)
                {poke->
                    PokeElement(poke, hubViewModel, scope)
                }
            }
        }

    }

}