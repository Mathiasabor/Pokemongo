package com.abor.myapplication.VUES.Welcome.Component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.abor.pokemongo.MODEL.API.TypElement
import com.abor.pokemongo.R
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

fun PokeType( modifier2 : Modifier, typElement: TypElement, nav : NavController, hubViewModel: HubViewModel, scope : CoroutineScope){

    ElevatedButton(
        onClick = {
            scope.launch {
                hubViewModel.horizontalGradient.value = typElement.name
                hubViewModel.byTypePokemon.value = hubViewModel.executeGetAllPokemonByType(hubViewModel.extraireEntier(typElement.url)!!)
                nav.navigate(Routes.pokemonPage.destination){
                    popUpTo(Routes.welcome.destination)
                }
            }
        },
        contentPadding = PaddingValues(),
        shape = RoundedCornerShape(15.dp),
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 10.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
        modifier = modifier2

    )
    {

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                    .background(brush = Brush.verticalGradient(ColorType.verticalGradients[typElement.name]!!))
                    .fillMaxSize()
        )
        {
                Image(painter = painterResource(id = R.drawable.pokeball128), contentDescription ="poked", )
                Text(text = typElement.name, fontSize = 20.sp, color = Color.White)
        }




    }
}

