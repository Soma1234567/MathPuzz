package com.soma.mathpuzz.presentation.splash_screen

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.soma.mathpuzz.R
import com.soma.mathpuzz.navigation.Screen
import kotlinx.coroutines.delay
import org.koin.core.scope.ScopeID

@Composable
fun SplashScreen(navController: NavHostController){
    val alpha = remember {
        Animatable(0f)
    }
    LaunchedEffect(key1 = true){
        alpha.animateTo(
            targetValue = 1f,
            animationSpec = tween(
                durationMillis = 1200,
                easing = FastOutLinearInEasing
            )
        )
        delay(100)
        navController.popBackStack()
        navController.navigate(Screen.HomeScreen.route)
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(120.dp)
                .alpha(alpha.value)
        ){
            Image(contentDescription = null, painter = painterResource(id = R.drawable.splash) )

        }
        Spacer(modifier = Modifier.height(15.dp))
        Text(
            text = "MathPuzz",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily(
                Font(R.font.robotoslab)
            ),
            modifier = Modifier.alpha(alpha.value)
        )
        Spacer(modifier = Modifier.height(20.dp))
    }
}