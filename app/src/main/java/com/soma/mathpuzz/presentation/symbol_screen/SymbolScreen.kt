package com.soma.mathpuzz.presentation.symbol_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.soma.mathpuzz.navigation.Screen

@Composable
fun SymbolScreen(){
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Column{
            SymbolText(text1 = "+", text2 = "Addition")
            SymbolText(text1 = "-", text2 = "Subtraction")
            SymbolText(text1 = "x", text2 = "Multiplication")
            SymbolText(text1 = "/", text2 = "Division")
            SymbolText(text1 = "%", text2 = "Reminder")
            SymbolText(text1 = "*", text2 = "Power")

        }
    }
}
@Composable
fun SymbolText(text1:String,text2:String){
    Text(
        text = "$text1       means  $text2",
        fontSize = 22.sp,
        color = Color.Black,
        fontWeight = FontWeight.Bold
    )
    Spacer(modifier = Modifier.height(20.dp))
}