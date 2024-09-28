package com.soma.mathpuzz.navigation

import java.util.logging.Level

sealed class Screen(val route:String) {
    object Splash:Screen(route = "splash_screen")
    object HomeScreen:Screen(route = "home_screen")
    object AllLevelsScreen:Screen(route = "all_levels_screen")
    object LevelScreen:Screen(route = "level_screen/{level}"){
        fun passLevel(level:Int):String{
            return "level_screen/$level"
        }
    }
    object RightAnswerScreen:Screen(route = "right_answer_screen/{level}"){
        fun passLevel(level: Int):String{
            return "right_answer_screen/$level"
        }
    }
    object WrongAnswerScreen : Screen(route ="wrong_answer_screen/{level}" ){
        fun passLevel(level: Int):String{
            return "wrong_answer_screen/$level"
        }
    }
    object SymbolScreen : Screen(route = "symbol_screen")
}