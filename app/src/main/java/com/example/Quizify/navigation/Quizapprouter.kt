package com.example.Quizify.navigation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

sealed class Screen(){
    object Signupactivity:Screen()
    object Termsandcondition:Screen()
    object Loginactivity:Screen()
    object Homeactivity:Screen()
    object Profileactivity:Screen()
    data class PracticeSets(val subjectName: String) : Screen()
    object Questionsactivity:Screen()
    object AdminActivities:Screen()
    object AddQuestions:Screen()
    object AdminLoginActivity:Screen()
    object ForgetPassword:Screen()
    object Admin:Screen()
    data class AddSet(val subjectName: String) : Screen()
}

object Quizapprouter {
    private val screenStack = mutableListOf<Screen>()
    val currentScreen: MutableState<Screen> = mutableStateOf(Screen.Loginactivity)
//    val currentScreen: MutableState<Screen> = mutableStateOf(Screen.AdminActivities)

    var currentSubjectName: String? = null
    var currentSetName: String? = null

    fun navigateTo(destination: Screen) {
        println("inside navigateToD $destination")
        screenStack.add(currentScreen.value)
        currentScreen.value = destination
        println("inside navigateToC ${currentScreen.value}")
    }
    fun navigateTo(destination: Screen.AddSet) {
        println("inside navigateTo1 $destination")
        screenStack.add(currentScreen.value)
        currentScreen.value = destination
    }

    fun navigateTo(destination: Screen.PracticeSets) {
        screenStack.add(currentScreen.value)
        currentScreen.value = destination
        println("inside navigateTo2 $destination")
    }

    fun navigateTo(destination: Screen.Questionsactivity, subjectName: String, setName: String) {
        screenStack.add(currentScreen.value)
        currentScreen.value = destination
        currentSubjectName = subjectName
        currentSetName = setName
        println("inside navigateTo2 $destination")
    }

    fun navigateBack() {
        if (screenStack.isNotEmpty()) {
            currentScreen.value = screenStack.removeAt(screenStack.size - 1)
        }
    }

    fun clearScreenStack() {
        screenStack.clear()
    }
}
