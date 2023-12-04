package com.abor.pokemongo

import android.annotation.SuppressLint

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.abor.myapplication.VUES.Splash.Splash
import com.abor.myapplication.VUES.Welcome.Welcome
import com.abor.pokemongo.VIEWMODEL.HubViewModel

import com.abor.pokemongo.VUES.POKEMONS.PokemonsPage

import com.abor.pokemongo.VUES.ROUTES.Routes
import com.abor.pokemongo.ui.theme.PokemonGoTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    /*dest est utilisé pour permettre le splash
    tout d'abord sa valeur par défaut est welcome
    dès qu'on rentre il est affecté à splash
    et après que la liste des types est chargé il est réaffecté à welcome
    ,*/

    var dest = mutableStateOf(Routes.welcome.destination)

    @SuppressLint("UnrememberedMutableState")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val hubViewModel = HubViewModel()
            val scope: CoroutineScope = rememberCoroutineScope()
            val navController = rememberNavController()

            dest.value = Routes.splash.destination
            LaunchedEffect(true ){

                scope.launch {
                    hubViewModel.typePokemon.value = hubViewModel.executeGetAllPokemonType()
                    navController.navigate(Routes.welcome.destination,)
                    dest.value = Routes.welcome.destination
                }

            }
            PokemonGoTheme {

                NavHost(navController = navController, startDestination = dest.value )
                {

                   composable(Routes.welcome.destination)
                   {
                       Welcome( hubViewModel, scope, navController)


                   }
                    composable(Routes.pokemonPage.destination)
                    {

                        PokemonsPage(scope, navController, hubViewModel)
                    }

                    composable(Routes.splash.destination)
                    {
                        Splash()
                    }


                }

            }


        }

    }
}


