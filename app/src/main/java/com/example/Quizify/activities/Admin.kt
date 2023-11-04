package com.example.Quizify.activities

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.Quizify.navigation.Quizapprouter
import com.example.Quizify.navigation.Screen

@Composable
fun Admin() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Quizapprouter.navigateTo(Screen.AdminActivities)
    }
}
