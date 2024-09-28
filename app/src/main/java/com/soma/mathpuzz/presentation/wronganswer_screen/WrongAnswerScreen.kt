package com.soma.mathpuzz.presentation.wronganswer_screen

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.soma.mathpuzz.R
import com.soma.mathpuzz.navigation.Screen

@Composable
fun WrongAnswerScreen(level:Int,navController: NavHostController){
    Box(
        modifier = Modifier.fillMaxSize(),

        ) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(220.dp)
            .align(Alignment.Center)){
            Image(painter = painterResource(id = R.drawable.failure), contentDescription =null , modifier = Modifier
                .size(150.dp)
                .align(Alignment.TopCenter))
            Column(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Wrong Answer!", fontSize = 23.sp, fontWeight = FontWeight.Bold)

            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Box(
            modifier = Modifier
                .padding(vertical = 20.dp)
                .width(300.dp)
                .height(45.dp)
                .background(
                    color = Color.Red.copy(alpha = 0.7f), shape = RoundedCornerShape(15.dp)
                )
                .align(Alignment.BottomCenter)
                .clickable {
                    navController.popBackStack(Screen.HomeScreen.route,inclusive = false)
                           navController.navigate(Screen.LevelScreen.passLevel(level))
                },
            contentAlignment = Alignment.Center
        ) { Text(text = "Try Again", fontSize = 22.sp, color = Color.White) }
    }
}