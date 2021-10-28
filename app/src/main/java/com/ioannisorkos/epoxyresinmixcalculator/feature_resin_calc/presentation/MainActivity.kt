package com.ioannisorkos.epoxyresinmixcalculator.feature_resin_calc.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode.Companion.Screen
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ioannisorkos.epoxyresinmixcalculator.feature_resin_calc.domain.model.Resin
import com.ioannisorkos.epoxyresinmixcalculator.feature_resin_calc.presentation.mix.MixScreen
import com.ioannisorkos.epoxyresinmixcalculator.feature_resin_calc.presentation.mix.MixViewModel
import com.ioannisorkos.epoxyresinmixcalculator.feature_resin_calc.presentation.resin_list.ResinListScreen
import com.ioannisorkos.epoxyresinmixcalculator.feature_resin_calc.presentation.setting.SettingScreen
import com.ioannisorkos.epoxyresinmixcalculator.feature_resin_calc.presentation.utill.BottomScreen
import com.ioannisorkos.epoxyresinmixcalculator.feature_resin_calc.presentation.utill.bottomNavigationItems
import com.ioannisorkos.epoxyresinmixcalculator.ui.theme.EpoxyResinMixCalculatorTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @ExperimentalMaterialApi
    @ExperimentalComposeUiApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EpoxyResinMixCalculatorTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    color = MaterialTheme.colors.background
                ) {

                    val navController = rememberNavController()
                    val scaffoldState = rememberScaffoldState()
                    val scope = rememberCoroutineScope()

                    Scaffold(
//                        floatingActionButton = {
//                            FloatingActionButton(
//                                onClick = {
//
//                                },
//                                backgroundColor = MaterialTheme.colors.primary
//                            ) {
//                                Icon(imageVector = Icons.Default.Add, contentDescription = "Add quote")
//                            }
//                        },
                        bottomBar = {
                            BottomNavigationBar(
                                bottomNavigationItems,
                                navController = navController,
                                onItemClick = {
                                    navController.navigate(it.route){
                                        navController.graph.startDestinationRoute?.let { route ->
                                            popUpTo(route) {
                                                saveState = true
                                            }
                                        }
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                }
                            )},
                        scaffoldState = scaffoldState
                    ){

                        Navigation(navController = navController)

                    }
                }
            }
        }
    }
}



    @ExperimentalComposeUiApi
    @Composable
    fun Navigation(navController: NavHostController) {
        NavHost(
            navController = navController,
            startDestination = BottomScreen.MixBottomScreen.route
        ) {
            composable(BottomScreen.MixBottomScreen.route) {
                MixScreen()
            }
            composable(BottomScreen.ResinsBottomScreen.route) {
                ResinListScreen()
            }
            composable(BottomScreen.Setting.route) {
                SettingScreen()
            }
        }
    }




    @Composable
    fun BottomNavigationBar(
        items: List<BottomScreen>,
        navController: NavController,
        modifier: Modifier = Modifier,
        onItemClick: (BottomScreen) -> Unit
    ) {

        val backStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = backStackEntry?.destination?.route

        BottomNavigation(
            modifier = modifier,
            backgroundColor = Color.DarkGray,
            elevation = 5.dp
        ) {
            items.forEach { item ->

                val selected = item.route == currentRoute

                BottomNavigationItem(
                    selected = selected,
                    onClick = {
                        onItemClick(item)
                              },
                    selectedContentColor= Color.White,
                    unselectedContentColor= Color.White.copy(alpha = 0.4f),
                    icon = {
                        Icon(
                                    imageVector = item.icon,
                                    contentDescription = item.title
                                )

                    },
                    label= {
                        Text(
                            text = item.title
                        )
                    },
                )
            }
        }
    }












@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    EpoxyResinMixCalculatorTheme {
        Greeting("Android")
    }
}