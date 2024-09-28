package com.soma.mathpuzz.presentation.all_levels_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.soma.mathpuzz.R
import com.soma.mathpuzz.navigation.Screen
import org.koin.androidx.compose.getViewModel

@Composable
fun AllLevelScreen(navController: NavHostController,
                   viewModel: AllLevelViewModel = getViewModel()){
    val state by viewModel.levelsCompleted.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        for(i in 0 until 23){
            Row(
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .fillMaxWidth(),
                horizontalArrangement = if(i!=22) Arrangement.SpaceBetween else Arrangement.spacedBy(27.dp)
            ) {

                for(j in 0..if(i!=22) 4 else 2){
                    Level(text = (i*5+j+1).toString(), isCompleted = (i*5+j)<=(state+1)) {
                        if((i*5+j)<=(state+1)){
                            navController.navigate(Screen.LevelScreen.passLevel(i*5+j))
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Level(
    text:String,
    isCompleted:Boolean,
    onClick:()->Unit
){
    Box(
        modifier = Modifier
            .size(50.dp)
            .background(
                color = if (isCompleted) Color(0xFF318CE7).copy(alpha = 0.6f) else Color(0xFFb38b6d),
                shape = RoundedCornerShape(12.dp)
            )
            .clickable {
                onClick()
            },
        contentAlignment = Alignment.Center
    ){
        if(isCompleted){
            Text(text = text, fontSize = 20.sp, color = Color.White)
        }
        else{
            Icon(painter = painterResource(id = R.drawable.lock), contentDescription = null, tint = Color.White, modifier = Modifier.size(35.dp))
        }

    }
}