package com.soma.mathpuzz.presentation.home_screen

import android.widget.Space
import androidx.annotation.DrawableRes
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.soma.mathpuzz.R
import com.soma.mathpuzz.navigation.Screen
import com.soma.mathpuzz.util.Animation
import org.koin.androidx.compose.getViewModel

@Composable
fun HomeScreen(navController: NavHostController,
               viewModel: HomeViewModel = getViewModel()){
    val state by viewModel.levelsCompleted.collectAsState()
    val alpha1 = remember {
        Animatable(0f)
    }
    val alpha2 = remember {
        Animatable(0f)
    }
    val alpha3 = remember {
        Animatable(0f)
    }
    LaunchedEffect(key1 = true){
        alpha1.animateTo(
            targetValue = 1f,
            animationSpec = tween(
                durationMillis = 1000,
                easing = LinearOutSlowInEasing
            )
        )
        alpha2.animateTo(
            targetValue = 1f,
            animationSpec = tween(
                durationMillis = 600,
                easing = LinearOutSlowInEasing
            )
        )
        alpha3.animateTo(
            targetValue = 1f,
            animationSpec = tween(
                durationMillis = 600,
                easing = LinearOutSlowInEasing
            )
        )
        Animation.isanimation = false
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(listOf(Color.White, Color.LightGray))),

    ) {
        Column(
            modifier = Modifier
                .padding(top = 30.dp)
                .fillMaxWidth()
                .align(Alignment.TopCenter),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "MathPuzz",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily(Font(R.font.robotoslab)),
            )
            Spacer(modifier = Modifier.height(15.dp))
            Text(text = "100+ math puzzles", fontSize = 23.sp, fontStyle = FontStyle.Normal)
        }
        Column(
            modifier = Modifier.align(Alignment.Center)
        ) {
            Feature(modifier =Modifier.alpha(if(Animation.isanimation) alpha1.value else 1f) , text = "Play",R.drawable.play) {
                navController.navigate(Screen.LevelScreen.passLevel(state+1))
            }
            Feature(modifier =Modifier.alpha(if(Animation.isanimation) alpha2.value else 1f) , text = "Levels",R.drawable.levels) {
                navController.navigate(Screen.AllLevelsScreen.route)
            }
            Feature(modifier =Modifier.alpha(if(Animation.isanimation) alpha3.value else 1f) , text = "Symbols",R.drawable.symbols) {
                navController.navigate(Screen.SymbolScreen.route)
            }
        }


    }
}

@Composable
fun Feature(
    modifier: Modifier,
    text:String,
    @DrawableRes image:Int,
    onClick:()->Unit
) {
    Box(
        modifier = modifier
            .padding(vertical = 8.dp)
            .width(165.dp)
            .height(50.dp)
            .background(
                color = Color(0xFF318CE7).copy(alpha = 0.6f),
                shape = RoundedCornerShape(15.dp)
            )
            .clickable {
                onClick()
            },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(id = image),
            contentDescription = null,
            modifier = Modifier
                .size(30.dp)
                .align(Alignment.CenterStart)
                .padding(start = 8.dp),
            tint = Color.White
        )
        Text(
            text = text, fontSize = 20.sp, color = Color.White, fontFamily = FontFamily(
                Font(R.font.robotoslab)
            ),
            modifier = Modifier.align(Alignment.Center)
        )
    }
}
