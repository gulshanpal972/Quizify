package com.example.Quizify.navigation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

sealed class Screen(){
    object Signupactivity:Screen()
    object Termsandcondition:Screen()
    object Loginactivity:Screen()
    object Homeactivity:Screen()
    object Profileactivity:Screen()
    object PracticeSets:Screen()
    object Questionsactivity:Screen()
    object AdminActivities:Screen()
    object AddQuestions:Screen()
    object AdminLoginActivity:Screen()
}

object Quizapprouter{
    var currentScreen:MutableState<Screen> = mutableStateOf(Screen.Loginactivity)

    fun navigateTo(destination:Screen){
        currentScreen.value=destination
    }
}