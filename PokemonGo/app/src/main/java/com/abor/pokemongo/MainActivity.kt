package com.abor.pokemongo

import android.annotation.SuppressLint

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold


import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope


import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import com.abor.myapplication.VUES.Splash.Splash3
import com.abor.myapplication.VUES.Welcome.Welcome
import com.abor.pokemongo.VIEWMODEL.HubViewModel
import com.abor.pokemongo.VUES.POKEMONS.PokemonDetailPage



import com.abor.pokemongo.VUES.ROUTES.Routes
import com.abor.pokemongo.ui.theme.PokemonGoTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    /*dest est utilisé pour permettre le splash
    tout d'abord sa valeur par défaut est welcome
    dès qu'on rentre il est affecté à splash
    et après que la liste des types est chargé il est réaffecté à welcome
    ,*/

    var dest = mutableStateOf(Routes.welcome.destination)

    @OptIn(ExperimentalMaterial3Api::class)
    @SuppressLint("UnrememberedMutableState", "UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val hubViewModel = HubViewModel()
            val scope: CoroutineScope = rememberCoroutineScope()
            val navController = rememberNavController()


            dest.value = Routes.splash.destination
            LaunchedEffect(true ){

                scope.async {
                    hubViewModel.typePokemon.value = hubViewModel.executeGetAllPokemonType()
                    hubViewModel._typePokemon.value.results.forEach{pok->
                        hubViewModel.pokemonsbytypelist.value.add(hubViewModel.executeGetAllPokemonByType(hubViewModel.extraireEntier(pok.url)!!))
                    }
                    navController.navigate(Routes.welcome.destination,)
                    dest.value = Routes.welcome.destination
                }

            }
          // PokemonGoTheme {
                Scaffold {
                    NavHost(navController = navController, startDestination = dest.value )
                    {

                        composable(Routes.welcome.destination)
                        {
                            Welcome( hubViewModel, scope, navController)


                        }


                        composable(Routes.splash.destination)
                        {
                            Splash3 ()
                        }

                        composable(Routes.pokemonDetailPage.destination)
                        {
                            PokemonDetailPage( navController,hubViewModel)
                        }


                    }
                }


         //  }


        }

    }
}



