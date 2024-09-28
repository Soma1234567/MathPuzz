package com.soma.mathpuzz.presentation.rightanswer_screen

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
fun RightAnswerScreen(level:Int,navController: NavHostController){
    Box(
        modifier = Modifier.fillMaxSize(),

    ) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(220.dp)
            .align(Alignment.Center)){
            Image(painter = painterResource(id = R.drawable.success), contentDescription =null , modifier = Modifier
                .size(150.dp)
                .align(Alignment.TopCenter))
            Column(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Congrats!", fontSize = 23.sp, fontWeight = FontWeight.Bold)
                Text(text = "You have completed level ${level+1}", fontSize = 20.sp, fontWeight = FontWeight.Normal)

            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Box(
            modifier = Modifier
                .padding(vertical = 20.dp)
                .width(300.dp)
                .height(45.dp)
                .background(
                    color = Color(0xFF318CE7).copy(alpha = 0.6f), shape = RoundedCornerShape(15.dp)
                )
                .align(Alignment.BottomCenter)
                .clickable {
                           navController.navigate(Screen.LevelScreen.passLevel(level+1))
                },
            contentAlignment = Alignment.Center
        ) { Text(text = "Next Level", fontSize = 22.sp, color = Color.White) }
    }
}