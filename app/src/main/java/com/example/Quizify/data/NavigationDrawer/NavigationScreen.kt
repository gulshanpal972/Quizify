package com.example.Quizify.data.NavigationDrawer

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.Quizify.activities.Aboutactivity
import com.example.Quizify.activities.Dashboardactivity
import com.example.Quizify.activities.Homeactivity
import com.example.Quizify.activities.Settingsactivity

@Composable
fun NavigationScreen(navController: NavHostController){

    NavHost(navController, startDestination = NavigationItem.Home.route){

        composable(NavigationItem.Home.route){
            Homeactivity()
        }

        composable(NavigationItem.Dashboard.route){
            Dashboardactivity()
        }

        composable(NavigationItem.Settings.route){
            Settingsactivity()
        }

        composable(NavigationItem.Favourite.route){
            Aboutactivity()
        }

    }

}