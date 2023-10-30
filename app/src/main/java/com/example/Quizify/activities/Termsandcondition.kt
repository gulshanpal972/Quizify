package com.example.Quizify.activities

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.Quizify.components.HeadingTextComponent
import com.example.Quizify.navigation.Quizapprouter
import com.example.Quizify.navigation.Screen
import com.example.Quizify.navigation.SystemBackButtonHandler

@Composable
fun Termsandcondition(){
    Surface(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.White)
        .padding(16.dp)
    ) {
        HeadingTextComponent(value = "Terms of Use")
        SystemBackButtonHandler {
            Quizapprouter.navigateTo(Screen.Signupactivity)
        }
    }
}