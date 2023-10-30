package com.example.Quizify.app

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.Quizify.MainScreen
import com.example.Quizify.activities.AddQuestions
import com.example.Quizify.activities.AdminActivity
import com.example.Quizify.activities.AdminLoginActivity
import com.example.Quizify.activities.Loginactivity
import com.example.Quizify.activities.PracticeSets
import com.example.Quizify.activities.Profileactivity
import com.example.Quizify.activities.Questionsactivity
import com.example.Quizify.activities.Signupactivity
import com.example.Quizify.activities.Termsandcondition
import com.example.Quizify.data.NavigationDrawer.HomeViewModel
import com.example.Quizify.navigation.Quizapprouter
import com.example.Quizify.navigation.Screen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Quizapp(homeViewModel: HomeViewModel= viewModel()){
    homeViewModel.checkActiveSession()
    Surface(
        modifier = Modifier.fillMaxSize(),
        color= Color.White
    ) {
        if(homeViewModel.isUserLoggedIn.value==true){
            Quizapprouter.navigateTo(Screen.Homeactivity)
        }

        Crossfade(targetState = Quizapprouter.currentScreen, label = "") { currentState->
            when(currentState.value){
                is Screen.Signupactivity->{
                    Signupactivity()
                }
                is Screen.Termsandcondition-> {
                    Termsandcondition()
                }
                is Screen.Loginactivity->{
                    Loginactivity()
                }

                is Screen.Homeactivity->{
                    MainScreen()
                }

                is Screen.Profileactivity->{
                    Profileactivity()
                }

                is Screen.PracticeSets->{
                    PracticeSets()
                }

                is Screen.Questionsactivity->{
                    Questionsactivity()
                }

                is Screen.AdminActivities->{
                    AdminActivity()
                }

                is Screen.AddQuestions->{
                    AddQuestions()
                }

                is Screen.AdminLoginActivity->{
                    AdminLoginActivity()
                }
            }
        }
    }
}