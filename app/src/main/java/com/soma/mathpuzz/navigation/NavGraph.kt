package com.soma.mathpuzz.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.soma.mathpuzz.presentation.all_levels_screen.AllLevelScreen
import com.soma.mathpuzz.presentation.home_screen.HomeScreen
import com.soma.mathpuzz.presentation.level_screen.LevelScreen
import com.soma.mathpuzz.presentation.rightanswer_screen.RightAnswerScreen
import com.soma.mathpuzz.presentation.splash_screen.SplashScreen
import com.soma.mathpuzz.presentation.symbol_screen.SymbolScreen
import com.soma.mathpuzz.presentation.wronganswer_screen.WrongAnswerScreen

@Composable
fun NavGraph(navController:NavHostController){
    NavHost(navController = navController, startDestination = Screen.Splash.route){
        composable(Screen.Splash.route){
           SplashScreen(navController)
        }
        composable(Screen.LevelScreen.route,
            arguments = listOf(
                    navArgument("level"){
                        type = NavType.IntType
                    }
            )
        ){
            val level = it.arguments?.getInt("level")
            LevelScreen(level!!,navController = navController)
        }
        composable(Screen.HomeScreen.route){
            HomeScreen(navController = navController)
        }
        composable(Screen.AllLevelsScreen.route){
            AllLevelScreen(navController = navController)
        }
        composable(Screen.RightAnswerScreen.route,
            arguments = listOf(
                navArgument("level"){
                    type = NavType.IntType
                }
            )
            ){
            val level = it.arguments?.getInt("level")
            RightAnswerScreen(level!!,navController = navController)
        }
        composable(Screen.WrongAnswerScreen.route,
            arguments = listOf(
                navArgument("level"){
                    type = NavType.IntType
                }
            )){
            val level = it.arguments?.getInt("level")

            WrongAnswerScreen(level!!,navController = navController)
        }
        composable(Screen.SymbolScreen.route){
            SymbolScreen()
        }
    }
}