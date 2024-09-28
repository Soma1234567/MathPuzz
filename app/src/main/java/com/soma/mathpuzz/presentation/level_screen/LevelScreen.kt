package com.soma.mathpuzz.presentation.level_screen

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.core.content.ContextCompat.startActivity
import androidx.core.content.FileProvider
import androidx.core.graphics.drawable.toBitmap
import androidx.navigation.NavHostController
import com.soma.mathpuzz.navigation.Screen
import com.soma.mathpuzz.util.answers
import com.soma.mathpuzz.util.hints
import com.soma.mathpuzz.util.questions
import org.koin.androidx.compose.getViewModel
import java.io.File
import java.io.FileOutputStream


@Composable
fun LevelScreen(
    level:Int,
    navController: NavHostController,
    viewModel: LevelViewModel = getViewModel()
){
    val context = LocalContext.current
    val state by viewModel.levelsCompleted.collectAsState()
    val answer = remember {
        mutableStateOf("")
    }
    var index  =  remember {
        mutableStateOf(level)
    }
    val question = questions.get(index.value)
    val hint = hints.get(index.value)
    val actualanswer = answers.get(index.value)
    var ishint = remember {
        mutableStateOf(false)
    }
    if(ishint.value){
        HintDialog(hint = hint) {
            ishint.value = false
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(
                    color = MaterialTheme.colorScheme.primary
                ),
            contentAlignment = Alignment.CenterStart
            
        ){
            Text(text = "Level ${index.value+1}", fontSize = 22.sp, color = Color.White, modifier = Modifier.padding(horizontal = 20.dp))
        }
        Spacer(modifier = Modifier.height(20.dp))

        Box(
            modifier = Modifier
                .width(300.dp)
                .height(400.dp)
                .background(color = Color.White, shape = RoundedCornerShape(15.dp))
        ){
            Image(painter = painterResource(id = question), contentDescription = null, modifier = Modifier.clip(
                RoundedCornerShape(15.dp)
            ))
        }
        Row(
            modifier = Modifier
                .padding(horizontal = 32.dp, vertical = 10.dp)
                .fillMaxWidth()
                .height(50.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .height(50.dp)
                    .background(
                        color = Color.Red.copy(alpha = 0.7f)/*Color(0xFF89CFF0)*/,
                        shape = RoundedCornerShape(10.dp)
                    )
                , contentAlignment = Alignment.CenterStart
            ){
                Text(text = answer.value, fontSize = 22.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(start = 8.dp), color = Color.White)
            }
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(
                        color = Color.Red.copy(alpha = 0.7f),
                        shape = RoundedCornerShape(10.dp)
                    )
                    .clickable {
                        navController.popBackStack(
                            route = Screen.HomeScreen.route,
                            inclusive = false
                        )
                        if (actualanswer.toString() == answer.value) {
                            if (level > state) {
                                viewModel.saveLevel(level)
                            }
                            navController.navigate(Screen.RightAnswerScreen.passLevel(level))
                        } else {
                            navController.navigate(Screen.WrongAnswerScreen.passLevel(level))
                        }

                    },
                contentAlignment = Alignment.Center
            ){
                Icon(imageVector = Icons.Default.Done, contentDescription = null, tint = Color.White)
            }
        }
        Row(
            modifier = Modifier
                .padding(horizontal = 32.dp, vertical = 12.dp)
                .fillMaxWidth()
                .height(50.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(
                        color = Color.Red.copy(alpha = 0.7f),
                        shape = RoundedCornerShape(10.dp)
                    )
                    .clickable {
                        answer.value = answer.value.substring(0, answer.value.length - 1)
                    },
                contentAlignment = Alignment.Center
            ){
                Icon(imageVector = Icons.Default.Clear, contentDescription = null, tint = Color.White)
            }
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(
                        color = Color.Red.copy(alpha = 0.7f),
                        shape = RoundedCornerShape(10.dp)
                    )
                    .clickable {
                        ishint.value = true
                    },
                contentAlignment = Alignment.Center
            ){
                Icon(imageVector = Icons.Default.Info, contentDescription = null, tint = Color.White)
            }
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(
                        color = Color.Red.copy(alpha = 0.7f),
                        shape = RoundedCornerShape(10.dp)
                    )
                    .clickable {
                        val bitmap = context
                            .getDrawable(question)
                            ?.toBitmap()
                        val imagefolder = File(context.getCacheDir(), "images");
                        var uri: Uri? = null;
                        try {
                            imagefolder.mkdirs();
                            val file = File(imagefolder, "math_puzzle.png");
                            val outputStream = FileOutputStream(file);
                            bitmap?.compress(Bitmap.CompressFormat.PNG, 90, outputStream);
                            outputStream.flush();
                            outputStream.close();
                            uri = FileProvider.getUriForFile(
                                context.applicationContext,
                                "com.soma.mathpuzz",
                                file
                            );
                        } catch (e: Exception) {
                            Toast.makeText(context,"some error",Toast.LENGTH_SHORT).show()
                        }
                        uri?.let {
                            val intent = Intent(Intent.ACTION_SEND)
                            intent.putExtra(Intent.EXTRA_STREAM, uri)
                            intent.putExtra(Intent.EXTRA_TEXT, "Are you able to solve this puzzle?")
                            intent.setType("image/*")
                            context.startActivity(Intent.createChooser(intent, "Share Via"))
                        }

                    },
                contentAlignment = Alignment.Center
            ){
                Icon(imageVector = Icons.Default.Share, contentDescription = null, tint = Color.White)
            }
        }
        Row(
            modifier = Modifier
                .padding(horizontal = 32.dp, vertical = 15.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            for(i in 0..4){
                Number(text = i.toString()) {
                    answer.value = answer.value+i.toString()
                }
            }
        }
        Row(
            modifier = Modifier
                .padding(horizontal = 32.dp, vertical = 10.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            for(i in 5..9){
                Number(text = i.toString()) {
                    answer.value = answer.value+i.toString()
                }
            }
        }

    }
}

@Composable
fun Number(text:String,onclick:()->Unit){
    Box(
        modifier = Modifier
            .size(45.dp)
            .background(
                color = Color.Red.copy(alpha = 0.7f),
                shape = RoundedCornerShape(10.dp)
            )
            .clickable {
                onclick()
            },
        contentAlignment = Alignment.Center
    ){
        Text(text = text, fontSize = 20.sp, color = Color.White)
    }
}

@Composable
fun HintDialog(
    @DrawableRes hint:Int,
    onDismiss:()->Unit
){
    Dialog(onDismissRequest = onDismiss, properties = DialogProperties(dismissOnClickOutside = false)) {
        Column(
            modifier = Modifier.width(300.dp)
        ){
            Image(painter = painterResource(id = hint), contentDescription = null, modifier = Modifier
                .fillMaxWidth()
                .height(170.dp))
            Spacer(modifier = Modifier.height(10.dp))
            Box(modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
                .background(color = Color.Gray, shape = RoundedCornerShape(10.dp))
                .clickable {
                    onDismiss()
                },
                contentAlignment = Alignment.Center){
                Text(text = "close", color = Color.White, fontSize = 21.sp)
            }
        }
    }
}